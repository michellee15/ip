package jerry.command;

import jerry.exceptions.InvalidCommandFormatException;
import jerry.exceptions.JerryException;
import jerry.storage.Storage;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * It parses the user input to extract the task number to be deleted and validates whether it is the correct format.
     * An exception will be thrown if the input is in invalid format or has invalid task number.
     *
     * @param input the user input in the expected format: "delete (task number)".
     * @throws InvalidCommandFormatException if the input is invalid or in an incorrect format.
     */
    public DeleteCommand(String input) throws InvalidCommandFormatException {
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
        this.response = taskList.deleteTask(index);
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
