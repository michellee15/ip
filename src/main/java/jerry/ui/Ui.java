package jerry.ui;

import java.util.Scanner;

/**
 * The Ui class handles all user interaction which includes displaying messages to the user,
 * reading user input, and presenting task-related information in a
 * human-friendly format.
 */
public class Ui {

    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Display a welcome message to the user as they first open the application.
     */
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
        System.out.println("Oops! " + message);
    }

    public void showLoadingError() {
        System.out.println("Failed to load resources. Please try again!");
    }


    public void displayOutput(String text) {
        System.out.println(text);
    }

}
