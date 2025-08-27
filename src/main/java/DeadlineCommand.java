import exceptions.InvalidCommandFormatException;
import exceptions.JerryException;
import storage.Storage;
import task.Deadline;
import tasklist.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command{

    private final String desc;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public DeadlineCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JerryException {
        try {
            String trimmed = desc.trim();
            if (trimmed.toLowerCase().startsWith("deadline ")) {
                trimmed = trimmed.substring(9).trim();
            }
            if (!trimmed.contains("/by")) {
                throw new InvalidCommandFormatException("Deadline must have '/by' keyword followed by due date");
            }
            String input = trimmed.substring(0, trimmed.indexOf("/by")).trim();
            String dateString = trimmed.substring(trimmed.indexOf("/by") + 3).trim();
            if (input.isEmpty() || dateString.isEmpty()) {
                throw new InvalidCommandFormatException("Uh Oh! Description and due date cannot be empty.");
            }
            LocalDateTime dueDate;
            try {
                dueDate = LocalDateTime.parse(dateString, DATE_TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new InvalidCommandFormatException("Invalid date/time format. " +
                        "Expected format: yyyy-MM-dd HH:mm (e.g., 2025-08-27 18:00)");
            }
            Deadline deadline = new Deadline(desc, dueDate);
            this.response = taskList.addTask(deadline);
            storage.writeToFile(taskList.toString());
        } catch (JerryException e) {
            throw new InvalidCommandFormatException("Invalid date/time format. " +
                    "Expected format: yyyy-MM-dd HH:mm (e.g., 2025-08-27 18:00)");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return this.response;
    }





}
