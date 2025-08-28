package jerry.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jerry.exceptions.InvalidCommandFormatException;
import jerry.exceptions.JerryException;

import jerry.storage.Storage;
import jerry.task.Deadline;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

public class DeadlineCommand extends Command {

    private String desc;
    private String dateString;

    public DeadlineCommand(String desc) throws InvalidCommandFormatException {
        String trimmed = desc.trim();
        if (!trimmed.toLowerCase().startsWith("deadline")) {
            throw new InvalidCommandFormatException("Deadline command must start with 'deadline'!");
        }
        String withoutCommand = trimmed.substring(8).trim();
        if (withoutCommand.isEmpty()) {
            throw new InvalidCommandFormatException("Description and due date cannot be empty.");
        }
        if (!withoutCommand.contains("/by")) {
            throw new InvalidCommandFormatException("Deadline must have '/by' keyword followed by the due date");
        }
        String[] parts = withoutCommand.split("/by", 2);
        this.desc = parts[0].trim();
        dateString = parts[1].trim();
        if (desc.isEmpty() || dateString.isEmpty()) {
            throw new InvalidCommandFormatException("Description and due date cannot be empty...");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JerryException {
        Deadline deadline = new Deadline(desc, dateString);
        this.response = taskList.addTask(deadline);
        taskList.saveTasks(storage);
        ui.displayOutput(this.response);
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
