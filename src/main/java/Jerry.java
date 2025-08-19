import java.util.Scanner;

public class Jerry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput;
        String[] tasks = new String[100];
        int count = 0;
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
            } else if (userInput.toLowerCase().equals("list")) {
                System.out.println("Your task list: ");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("___________________________________________________");
            } else {
                tasks[count] = userInput;
                count++;
                System.out.println("new task added: " + userInput);
                System.out.println("___________________________________________________");
            }
        }
    }
}
