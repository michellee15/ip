import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDateTime dueDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Deadline(String desc) throws InvalidCommandFormatException {
       super(parseDesc(desc));
       String date = parseDate(desc);
       try {
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
           this.dueDate = LocalDateTime.parse(date, formatter);
       } catch (DateTimeParseException e) {
           throw new InvalidCommandFormatException("Uh Oh! Invalid date/time format, use yyyy-MM-dd hour and min (eg. 2019-10-05 1800)");
       }
    }

    private static String parseDesc(String desc) throws InvalidCommandFormatException {
        if (desc.toLowerCase().startsWith("deadline ")) {
            desc = desc.substring(9).trim();
        }
        if (!desc.contains("by")) {
            throw new InvalidCommandFormatException("Deadline must have 'by' keyword followed by due date");
        }
        String taskDesc = desc.substring(0, desc.indexOf("by")).trim();
        if (taskDesc.isEmpty()) {
            throw new InvalidCommandFormatException("Uh Oh! You forgot to describe your task and due date!");
        }
        return taskDesc;
    }

    private static String parseDate(String desc) throws InvalidCommandFormatException {
        if (!desc.contains("by")) throw new InvalidCommandFormatException("Uh Oh! Deadline must have a due date or time");
        String by = desc.substring(desc.indexOf("by") + 3).trim();
        if (by.isEmpty()) throw new InvalidCommandFormatException("The due date or time cannot be empty!");
        return by;
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + this.dueDate.format(formatter);
    }

    @Override
    public String toString() {
        return "[DUE]" + super.toString() + " (by: " + this.dueDate.format(formatter) + ")";
    }

}