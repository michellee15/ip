import command.EventCommand;
import command.TodoCommand;
import exceptions.InvalidCommandException;
import exceptions.InvalidCommandFormatException;
import exceptions.JerryException;
import storage.Storage;
import task.Task;
import task.Deadline;
import task.Events;
import task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Jerry {
    public enum Command {
        BYE, LIST, DEADLINE, EVENT, TODO, MARK, UNMARK, DELETE
    }
    public static void main(String[] args) throws JerryException {
        Scanner sc = new Scanner(System.in);
        String userInput;
        Storage storage = new Storage("./data/jerry.txt");
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = storage.loadToFile();
            tasks = loadTasks(file);
        } catch (JerryException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("___________________________________________________");
        System.out.println("Hello, nice to meet you! I'm Jerry the mouse!");
        System.out.println("What can I do for you today?");
        System.out.println("___________________________________________________");

        while (sc.hasNextLine()) {
            try {
                userInput = sc.nextLine();
                System.out.println("___________________________________________________");
                String[] entries = userInput.split(" ", 2);
                Command command;
                try {
                    command = Command.valueOf(entries[0].toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new InvalidCommandException("Sorry! I don't understand what you mean by: " + userInput +
                            "\nUse these commands at the start of your sentence instead: " +
                            "bye/list/todo/deadline/event/mark/unmark/delete");
                }
                switch (command) {
                case BYE:
                    System.out.println("Bye! See you next time :D");
                    System.out.println("___________________________________________________");
                    return;
                case LIST:
                    if (tasks.isEmpty()) {
                        throw new JerryException("Your task list is currently empty...");
                    }
                    System.out.println("Your task list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println("___________________________________________________");
                    break;
                case DEADLINE:
                    if (entries.length < 2) {
                        throw new JerryException("You need to specify the task description and the due date");
                    }
                    Task deadline = new Deadline(entries[1]);
                    tasks.add(deadline);
                    System.out.println("Nice! This task has been added to the list:\n" + deadline);
                    System.out.println("Now you have " + tasks.size() + " in your list :)");
                    System.out.println("___________________________________________________");
                    saveTasks(storage, tasks);
                    break;
                case EVENT:
                    if (entries.length < 2) {
                        throw new InvalidCommandFormatException("Uh Oh! You need to specify the event description and a timeframe (its starting time and ending time)");
                    }
                    EventCommand eventCommand = new EventCommand(entries[1]);
                    Events event = eventCommand.run();
                    tasks.add(event);
                    System.out.println("Okay! I've added this to your task list:\n" + event);
                    System.out.println("Now you have " + tasks.size() + " in your list :)");
                    System.out.println("___________________________________________________");
                    saveTasks(storage, tasks);
                    break;
                case TODO:
                    if (entries.length < 2) {
                        throw new InvalidCommandFormatException("Uh Oh! You forgot to describe what your todo is...");
                    }
                    TodoCommand todoCommand = new TodoCommand(entries[1]);
                    ToDo toDo = todoCommand.run();
                    tasks.add(toDo);
                    System.out.println("Great! New task added: " + toDo);
                    System.out.println("Now you have " + tasks.size() + " in your list :)");
                    System.out.println("___________________________________________________");
                    saveTasks(storage, tasks);
                    break;
                case MARK:
                    int markIdx = checkIndex(entries, tasks.size());
                    tasks.get(markIdx).mark();
                    System.out.println("Yay! One task down: " + tasks.get(markIdx));
                    System.out.println("___________________________________________________");
                    saveTasks(storage, tasks);
                    break;
                case UNMARK:
                    int unmarkIdx = checkIndex(entries, tasks.size());
                    tasks.get(unmarkIdx).unmark();
                    System.out.println("Noted! I've marked this task as undone: " + tasks.get(unmarkIdx));
                    System.out.println("___________________________________________________");
                    saveTasks(storage, tasks);
                    break;
                case DELETE:
                    int delIdx = checkIndex(entries, tasks.size());
                    System.out.println("Noted! I've marked this task as deleted: " + tasks.get(delIdx));
                    tasks.remove(delIdx);
                    System.out.println("Now you have " + tasks.size() + " in your list :)");
                    System.out.println("___________________________________________________");
                    saveTasks(storage, tasks);
                    break;

                }
            } catch (JerryException e) {
                System.out.println(e.getMessage());
                System.out.println("___________________________________________________");
            }
        }
    }

    private static int checkIndex(String[] entries, int size) throws JerryException {
        if (entries.length < 2 || entries[1].trim().isEmpty()) {
            throw new InvalidCommandFormatException("Task.Task number must be a positive integer!");
        }
        try {
            int idx = Integer.parseInt(entries[1].trim()) - 1;
            if (idx < 0 || idx >= size) {
                throw new InvalidCommandFormatException("Oops! The task number you entered is invalid...Please try again!");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new JerryException("Task.Task number must be a positive integer!");
        }
    }

    private static ArrayList<Task> loadTasks(File file) {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] details = line.split("\\s*\\|\\s*");
                switch (details[0]) {
                case "T" :
                    ToDo todo = new ToDo(details[2].trim());
                    if (details[1].equals("1")) todo.mark();
                    tasks.add(todo);
                    break;
                case "D" :
                    Deadline deadline = new Deadline(details[2].trim() + " by " + details[3].trim());
                    if (details[1].trim().equals("1")) deadline.mark();
                    tasks.add(deadline);
                    break;
                case "E" :
                    if (details.length != 5) {
                        throw new JerryException("");
                    }
                    String desc = details[2].trim();
                    String[] fromDateTime = details[3].trim().split(" ");
                    String[] toDateTime = details[4].trim().split(" ");
                    tasks.add(new Events(desc, fromDateTime[0], fromDateTime[1], toDateTime[0], toDateTime[1]));
                    break;
                }
            }
        } catch (JerryException e) {
            System.out.println("Warning: Could not load some tasks due to file corruption or format issues");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    private static void saveTasks(Storage storage, ArrayList<Task> task) {
        StringBuilder sb = new StringBuilder();
        for (Task t : task) {
            sb.append(t.toFileString()).append("\n");
        }
        try {
            storage.writeToFile(sb.toString());
        } catch (JerryException e) {
            System.out.println(e.getMessage());
        }
    }

}