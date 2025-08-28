package jerry.exceptions;

/**
 * Represents an exception that is thrown when the user input does not follow the expected format.
 * <p>
 * This exception is to indicate errors like missing task description, incorrect keyword, or missing task number,
 * and display with appropriate error messages to guide users to the correct input format.
 */
public class InvalidCommandFormatException extends JerryException {
    public InvalidCommandFormatException(String message) {
        super(message);
    }
}
