import java.util.ArrayList;
import java.util.Scanner;

public class Jerry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput;
        ArrayList<Task> tasks = new ArrayList<>();

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
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println("___________________________________________________");
                    break;
                case ("mark"):
                    int markIdx = Integer.parseInt(entries[1].trim()) - 1;
                    tasks.get(markIdx).mark();
                    System.out.println("Yay! One task down: " + tasks.get(markIdx));
                    System.out.println("___________________________________________________");
                    break;
                case ("unmark"):
                    int unmarkIdx = Integer.parseInt(entries[1].trim()) - 1;
                    tasks.get(unmarkIdx).unmark();
                    System.out.println("Noted! I've marked this task as undone: " + tasks.get(unmarkIdx));
                    System.out.println("___________________________________________________");
                    break;
                case ("deadline"):
                    String[] parts = entries[1].split("by", 2);
                    Task deadline = new Deadline(parts[0].trim(), parts[1].trim());
                    tasks.add(deadline);
                    System.out.println("Nice! This task has been added to the list: \n" + deadline);
                    System.out.println("Now you have " + tasks.size() + " in your list :)");
                    System.out.println("___________________________________________________");
                    break;
                case ("event"):
                    String[] events = entries[1].split("from|to");
                    Task event = new Events(events[0].trim(), events[1].trim(), events[2].trim());
                    tasks.add(event);
                    System.out.println("Okay! I've added this to your task list: \n" + event);
                    System.out.println("Now you have " + tasks.size() + " in your list :)");
                    System.out.println("___________________________________________________");
                    break;
                case ("todo"):
                    Task todo = new ToDo(entries[1]);
                    tasks.add(todo);
                    System.out.println("Great! New task added: " + userInput);
                    System.out.println("Now you have " + tasks.size() + " in your list :)");
                    System.out.println("___________________________________________________");
                    break;
                default:
                    System.out.println("___________________________________________________");

            }
        }
    }
}
