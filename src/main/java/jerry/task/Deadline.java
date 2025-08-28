package jerry.task;

import jerry.exceptions.InvalidCommandFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDateTime dueDate;
    private static final DateTimeFormatter FILE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");


    public Deadline(String desc, String dateString) throws InvalidCommandFormatException {
       super(desc);
       try {
            dueDate = LocalDateTime.parse(dateString, FILE_FORMATTER);
       } catch (DateTimeParseException e) {
           throw new InvalidCommandFormatException("Invalid date/time format. " +
                    "Expected format: yyyy-MM-dd HH:mm (e.g., 2025-08-27 18:00)");
       }
    }

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