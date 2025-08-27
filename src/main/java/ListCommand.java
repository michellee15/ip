import exceptions.JerryException;
import storage.Storage;
import tasklist.TaskList;

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
