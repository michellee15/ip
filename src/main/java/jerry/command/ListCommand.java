package jerry.command;

import jerry.storage.Storage;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

/**
 * Represents a command to display all the tasks that are currently stored in file.
 * <p>
 * This class retrieves all the tasks from the task list, formatting them into a
 * readable string and display them to the user,
 * without any modification on the content of the task list.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        this.response = taskList.getList();
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
