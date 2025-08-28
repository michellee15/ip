package jerry.exceptions;

/**
 * Represents an exception that is thrown when a user attempts to access a task number
 * that is greater than the number of tasks available in the task list.
 * <p>
 * This exception is to prevent invalid operations on task list and provide users with
 * appropriate error messages.
 */
public class ListIndexOutOfBoundException extends JerryException {
    public ListIndexOutOfBoundException(String message) {
        super(message);
    }
}
