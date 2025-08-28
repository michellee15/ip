package jerry.task;

import jerry.exceptions.InvalidCommandFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task in the Jerry application.
 * <p>
 * A Deadline is a type of Task that has a due date and time.
 * It provides methods to parse and format the due date for both file storage
 * and user-friendly display.
 * The class also validates the input date string and throws an
 * exception if the format is invalid.
 */
public class Deadline extends Task {
    private final LocalDateTime dueDate;
    private static final DateTimeFormatter FILE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    /**
     * Constructs a Deadline task with a description and a due date string.
     *
     * @param desc the description of the task.
     * @param dateString the due date string in the format "yyyy-MM-dd HH:mm".
     * @throws InvalidCommandFormatException if the date string is invalid or incorrectly formatted.
     */
    public Deadline(String desc, String dateString) throws InvalidCommandFormatException {
       super(desc);
       try {
            dueDate = LocalDateTime.parse(dateString, FILE_FORMATTER);
       } catch (DateTimeParseException e) {
           throw new InvalidCommandFormatException("Invalid date/time format. " +
                    "Expected format: yyyy-MM-dd HH:mm (e.g., 2025-08-27 18:00)");
       }
    }

    /**
     * Method to create a Deadline task from file data.
     * <p>
     * This method is used when loading tasks from storage and ensures
     * proper parsing and validation of the due date.
     *
     * @param desc the description of the task.
     * @param dateString the due date string read from the file.
     * @return a new Deadline object.
     * @throws InvalidCommandFormatException if the date string is invalid or cannot be parsed.
     */
    public static Deadline fromFile(String desc, String dateString) throws InvalidCommandFormatException {
        try {
            return new Deadline(desc, dateString);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Unable to load deadline task: " + dateString);
        }
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() +
                " | " + this.dueDate.format(FILE_FORMATTER);
    }

    @Override
    public String toString() {
        return "[DUE]" + super.toString() +
                " (by: " + this.dueDate.format(DISPLAY_FORMATTER) + ")";
    }

}