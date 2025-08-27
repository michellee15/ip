package task;

import exceptions.InvalidCommandFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime dueDate;
    private static final DateTimeFormatter FILE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");


    public Deadline(String desc, LocalDateTime dueDate) throws InvalidCommandFormatException {
       super(desc);
       this.dueDate = dueDate;
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