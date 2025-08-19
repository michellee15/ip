import java.util.Scanner;

public class Jerry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput;
        Tasks[] tasks = new Tasks[100];
        int count = 0;

        System.out.println("___________________________________________________");
        System.out.println("Hello, nice to meet you! I'm Jerry the mouse!");
        System.out.println("What can I do for you today?");
        System.out.println("___________________________________________________");

        while (true) {
            userInput = sc.nextLine();
            System.out.println("___________________________________________________");
            String[] entries = userInput.split(" ", 2);
            String command = entries[0].toLowerCase();
            switch(command) {
                case ("bye"):
                    System.out.println("Bye! See you next time :)");
                    System.out.println("___________________________________________________");
                    return;
                case ("list"):
                    System.out.println("Your task list: ");
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                    System.out.println("___________________________________________________");
                    break;
                case ("mark"):
                    int markIdx = Integer.parseInt(entries[1].trim()) - 1;
                    tasks[markIdx].mark();
                    System.out.println("Yay! One task down: " + tasks[markIdx]);
                    System.out.println("___________________________________________________");
                    break;
                case ("unmark"):
                    int unmarkIdx = Integer.parseInt(entries[1].trim()) - 1;
                    tasks[unmarkIdx].unmark();
                    System.out.println("Noted! I've marked this task as undone: " + tasks[unmarkIdx]);
                    System.out.println("___________________________________________________");
                    break;
                default:
                    tasks[count] = new Tasks(userInput);
                    count++;
                    System.out.println("new task added: " + userInput);
                    System.out.println("___________________________________________________");
            }
        }
    }
}
