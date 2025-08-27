import java.util.Scanner;

import exceptions.JerryException;
import tasklist.TaskList;
import task.Task;

public class Ui {

    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("___________________________________________________");
        System.out.println("Hello, nice to meet you! I'm Jerry the mouse!");
        System.out.println("What can I do for you today?");
        System.out.println("___________________________________________________");
    }

    public String readInput() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("___________________________________________________");
    }

    public void showError(String message) {
        System.out.println("Uh Oh! " + message);
    }

    public void showExit() {
        System.out.println("Bye! See you next time :D");
    }

    public void showAdded(Task task, int size) {
        System.out.println("Nice! This task has been added to the list:\n" + task);
        System.out.println("Now you have " + size + " in your list :)");
    }

    public void showDeleted(Task task, int size) {
        System.out.println("Noted! I've marked this task as deleted: " + task);
        System.out.println("Now you have " + size + " in your list :)");
    }

    public void showMarked(Task task) {
        System.out.println("Yay! One task down:\n" + task);
    }

    public void showUnmarked(Task task) {
        System.out.println("Noted! I've marked this task as undone:\n" + task);
    }

    public void showLoadingError() {
        System.out.println("Oops! Failed to load resources. Please try again!");
    }

    public void showTaskList(TaskList taskList) throws JerryException {
        if (taskList.getSize() == 0) {
            throw new JerryException("Your task list is currently empty...");
        }
        System.out.println("Your task list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    public void displayOutput(String text) {
        System.out.println(text);
    }

}
