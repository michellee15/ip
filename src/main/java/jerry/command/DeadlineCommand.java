package jerry.command;

import jerry.exceptions.InvalidCommandFormatException;
import jerry.exceptions.JerryException;
import org.jetbrains.annotations.NotNull;
import jerry.storage.Storage;
import jerry.task.Deadline;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    private final String desc;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public DeadlineCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JerryException {
        String trimmed = desc.trim();
        if (!trimmed.toLowerCase().startsWith("deadline")) {
            throw new InvalidCommandFormatException("Deadline jerry.command must start with 'deadline'!");
        }
        String withoutCommand = trimmed.substring(8).trim();
        if (withoutCommand.isEmpty()) {
            throw new InvalidCommandFormatException("Description and due date cannot be empty.");
        }
        if (!withoutCommand.contains("/by")) {
            throw new InvalidCommandFormatException("Deadline must have '/by' keyword followed by the due date");
        }
        Deadline deadline = getDeadline(withoutCommand);
        this.response = taskList.addTask(deadline);
        taskList.saveTasks(storage);
        ui.displayOutput(this.response);
    }

    @NotNull
    private static Deadline getDeadline(String withoutCommand) throws InvalidCommandFormatException {
        String[] parts = withoutCommand.split("/by", 2);
        String input = parts[0].trim();
        String dateString = parts[1].trim();
        if (input.isEmpty() || dateString.isEmpty()) {
            throw new InvalidCommandFormatException("Description and due date cannot be empty...");
        }
        LocalDateTime dueDate;
        try {
            dueDate = LocalDateTime.parse(dateString, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Invalid date/time format. " +
                    "Expected format: yyyy-MM-dd HH:mm (e.g., 2025-08-27 18:00)");
        }
        return new Deadline(input, dueDate);
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
