import exceptions.JerryException;

import storage.Storage;
import tasklist.TaskList;


public class ByeCommand extends Command {

    public String end() {
        return "Bye! See you next time :D";
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws JerryException {
        ui.displayOutput(this.end());
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getString() {
        this.response = this.end();
        return this.response;
    }

}
