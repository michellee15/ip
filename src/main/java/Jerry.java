import java.util.Scanner;

public class Jerry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput;
        System.out.println("___________________________________________________");
        System.out.println("Hello, nice to meet you! I'm Jerry the mouse!");
        System.out.println("What can I do for you today?");
        System.out.println("___________________________________________________");
        while (true) {
            userInput = sc.nextLine();
            System.out.println("___________________________________________________");
            if (userInput.toLowerCase().equals("bye")) {
                System.out.println("Bye! See you next time :)");
                System.out.println("___________________________________________________");
                break;
            } else {
                System.out.println(userInput);
                System.out.println("___________________________________________________");
            }
        }
    }
}
