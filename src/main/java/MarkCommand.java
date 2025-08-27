import exceptions.InvalidCommandFormatException;
import exceptions.JerryException;
import storage.Storage;
import tasklist.TaskList;

public class MarkCommand extends Command {

    private int index = 0;

    public MarkCommand(String input) throws InvalidCommandFormatException {
        String[] entries = input.split(" ", 2);
        if (entries.length < 2 || entries[1].trim().isEmpty()) {
            throw new InvalidCommandFormatException("Task number must be positive!");
        }
        try {
            index = Integer.parseInt(entries[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException("Task number must be positive!");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JerryException {
        this.response = taskList.mark(index);
        storage.writeToFile(taskList.toString());
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
