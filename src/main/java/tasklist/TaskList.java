package tasklist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.JerryException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.ToDo;
import task.Task;

public class TaskList {
    private static final List<Task> taskList = new ArrayList<>();

    public TaskList(File file) throws JerryException {
        loadTasks(file);
    }

    public TaskList() {
    }

    private void loadTasks(File file) throws JerryException {
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] details = line.split("\\s*\\|\\s*");
                switch (details[0]) {
                case "T":
                    ToDo todo = new ToDo(details[2].trim());
                    if (details[1].equals("1")) todo.mark();
                    taskList.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(details[2].trim() + " by " + details[3].trim());
                    if (details[1].trim().equals("1")) deadline.mark();
                    taskList.add(deadline);
                    break;
                case "E":
                    if (details.length != 5) {
                        throw new JerryException("");
                    }
                    String desc = details[2].trim();
                    String[] fromDateTime = details[3].trim().split(" ");
                    String[] toDateTime = details[4].trim().split(" ");
                    taskList.add(new Event(desc, fromDateTime[0], fromDateTime[1], toDateTime[0], toDateTime[1]));
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new JerryException("Warning: Could not load some tasks due to file corruption or format issues");
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task get(int taskIndex) {
        return taskList.get(taskIndex);
    }

    public Task delete(int taskIndex) {
        return taskList.remove(taskIndex);
    }

    public void mark(int taskIndex) {
        taskList.get(taskIndex).mark();
    }

    public void unmark(int taskIndex) {
        taskList.get(taskIndex).unmark();
    }

    public int getSize() {
        return taskList.size();
    }

    public List<Task> getAll() {
        return new ArrayList<>(taskList);
    }

    public void saveTasks(Storage storage) {
        StringBuilder sb = new StringBuilder();
        for (Task t : taskList) {
            sb.append(t.toFileString()).append("\n");
        }
        try {
            storage.writeToFile(sb.toString());
        } catch (JerryException e) {
            System.out.println(e.getMessage());
        }
    }
}
