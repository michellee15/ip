package task;

import exceptions.InvalidCommandFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDateTime dueDate;
    private static final DateTimeFormatter FILE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");


    public Deadline(String desc) throws InvalidCommandFormatException {
       super(parseDesc(desc));
       String date = parseDate(desc);
       try {
           this.dueDate = LocalDateTime.parse(date, FILE_FORMATTER);
       } catch (DateTimeParseException e) {
           throw new InvalidCommandFormatException("Uh Oh! Invalid date/time format, use yyyy-MM-dd hour and min (eg. 2019-10-05 18:00)");
       }
    }

    private static String parseDesc(String desc) throws InvalidCommandFormatException {
        if (desc.toLowerCase().startsWith("deadline ")) {
            desc = desc.substring(9).trim();
        }
        if (!desc.contains("/by")) {
            throw new InvalidCommandFormatException("Task.Deadline must have '/by' keyword followed by due date");
        }
        String taskDesc = desc.substring(0, desc.indexOf("/by")).trim();
        if (taskDesc.isEmpty()) {
            throw new InvalidCommandFormatException("Uh Oh! You forgot to describe your task and due date!");
        }
        return taskDesc;
    }

    private static String parseDate(String desc) throws InvalidCommandFormatException {
        if (!desc.contains("/by")) throw new InvalidCommandFormatException(
                "Uh Oh! Task.Deadline must have a due date or time");
        String by = desc.substring(desc.indexOf("/by") + 3).trim();
        if (by.isEmpty()) throw new InvalidCommandFormatException("The due date or time cannot be empty!");
        return by;
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