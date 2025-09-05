package jerry.exceptions;

/**
<<<<<<< HEAD
 * Represents an exception that is thrown when a user wants to access a task number
 * that is greater than the number of tasks available in the task list.
 * This exception prevents invalid operations on task list and provide users with
=======
 * An exception that is thrown when a user tries to access a task number
 * that is greater than the task list size.
 * This exception is to prevent invalid operations on task list and provide users with
>>>>>>> branch-Level-10
 * appropriate error messages.
 */
public class ListIndexOutOfBoundException extends JerryException {
    public ListIndexOutOfBoundException(String message) {
        super(message);
    }
}
