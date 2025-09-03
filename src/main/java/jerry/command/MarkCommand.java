package jerry.command;

import jerry.exceptions.InvalidCommandFormatException;
import jerry.exceptions.JerryException;
import jerry.storage.Storage;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

/**
 * Represents a command to mark a task as completed.
 * This class checks the user input, ensuring that the task number is a
 * positive integer.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Construct a MarkCommand object based on user input.
     * The user input must be in the format: "mark task number".
     *
     * @param input user input containing task number.
     * @throws InvalidCommandFormatException if the input format is invalid or task number is not a positive integer.
     */
    public MarkCommand(String input) throws InvalidCommandFormatException {
        String[] entries = input.split(" ", 2);
        if (entries.length < 2 || entries[1].trim().isEmpty()) {
            throw new InvalidCommandFormatException("Task number must be positive!");
        }
        try {
            index = Integer.parseInt(entries[1]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException("Task number must be positive!");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JerryException {
        this.response = taskList.mark(index);
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
