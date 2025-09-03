package jerry.command;

import jerry.exceptions.InvalidCommandFormatException;
import jerry.exceptions.JerryException;
import jerry.storage.Storage;
import jerry.task.Deadline;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

/**
 * Parses the user input and adds the task to the task list when executed.
 * It ensures the input follows the expected syntax; starting with "deadline", and include
 * a "/by" keyword followed by the due date.
 */
public class DeadlineCommand extends Command {

    private final String desc;
    private final String dateString;

    /**
     * Constructs a new DeadlineCommand by parsing the user input which is expected to follow
     * the format: "deadline task /by due date".
     * An exception is thrown if the format is invalid or if the user missed out the task description.
     *
     * @param desc the user input string to be parsed.
     * @throws InvalidCommandFormatException if the expected format is not followed.
     */
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
