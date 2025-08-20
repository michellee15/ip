import java.util.ArrayList;
import java.util.Scanner;

public class Jerry {
    public static void main(String[] args) throws JerryException {
        Scanner sc = new Scanner(System.in);
        String userInput;
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("___________________________________________________");
        System.out.println("Hello, nice to meet you! I'm Jerry the mouse!");
        System.out.println("What can I do for you today?");
        System.out.println("___________________________________________________");

        while (true) {
            try {
                userInput = sc.nextLine();
                System.out.println("___________________________________________________");
                String[] entries = userInput.split(" ", 2);
                String command = entries[0].toLowerCase();
                switch (command) {
                    case ("bye"):
                        System.out.println("Bye! See you next time :D");
                        System.out.println("___________________________________________________");
                        return;
                    case ("list"):
                        if (tasks.isEmpty()) {
                            throw new JerryException("Your task list is currently empty...");
                        }
                        System.out.println("Your task list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                        System.out.println("___________________________________________________");
                        break;
                    case ("mark"):
                        if (entries.length < 2 || entries[1].trim().isEmpty()) {
                            throw new JerryException("Please specify which task number you want to mark!");
                        }
                        int markIdx = Integer.parseInt(entries[1].trim()) - 1;
                        if (markIdx < 0 || markIdx >= tasks.size()) {
                            throw new ListIndexOutOfBoundException("The task number you entered is invalid...Please try again!");
                        }
                        tasks.get(markIdx).mark();
                        System.out.println("Yay! One task down: " + tasks.get(markIdx));
                        System.out.println("___________________________________________________");
                        break;
                    case ("unmark"):
                        if (entries.length < 2 || entries[1].trim().isEmpty()) {
                            throw new JerryException("Please specify which task number you want to unmark!");
                        }
                        int unmarkIdx = Integer.parseInt(entries[1].trim()) - 1;
                        if (unmarkIdx < 0 || unmarkIdx >= tasks.size()) {
                            throw new ListIndexOutOfBoundException("The task number you entered is invalid...Please try again!");
                        }
                        tasks.get(unmarkIdx).unmark();
                        System.out.println("Noted! I've marked this task as undone: " + tasks.get(unmarkIdx));
                        System.out.println("___________________________________________________");
                        break;
                    case ("deadline"):
                        if (entries.length < 2) {
                            throw new JerryException("You need to specify the task description and the due date");
                        }
                        Task deadline = new Deadline(userInput);
                        tasks.add(deadline);
                        System.out.println("Nice! This task has been added to the list:\n" + deadline);
                        System.out.println("Now you have " + tasks.size() + " in your list :)");
                        System.out.println("___________________________________________________");
                        break;
                    case ("event"):
                        if (entries.length < 2) {
                            throw new InvalidCommandFormatException("Uh Oh! You need to specify the event description and a timeframe (its starting time and ending time)");
                        }
                        String[] events = entries[1].split("from|to");
                        Task event = new Events(userInput);
                        tasks.add(event);
                        System.out.println("Okay! I've added this to your task list:\n" + event);
                        System.out.println("Now you have " + tasks.size() + " in your list :)");
                        System.out.println("___________________________________________________");
                        break;
                    case ("todo"):
                        if (entries.length < 2) {
                            throw new InvalidCommandFormatException("Uh Oh! You forgot to describe what your todo is...");
                        }
                        Task todo = new ToDo(userInput);
                        tasks.add(todo);
                        System.out.println("Great! New task added: " + todo);
                        System.out.println("Now you have " + tasks.size() + " in your list :)");
                        System.out.println("___________________________________________________");
                        break;
                    default:
                        throw new InvalidCommandException("Sorry! I don't understand what you mean by: " + userInput +
                                "\nUse these commands at the start of your sentence instead: " +
                                "bye/list/todo/deadline/event/mark/unmark");

                }
            } catch (JerryException e) {
                System.out.println(e.getMessage());
                System.out.println("___________________________________________________");
            }
        }
    }
}
