package command;

import exceptions.InvalidCommandFormatException;
import exceptions.JerryException;
import storage.Storage;
import tasklist.TaskList;
import task.ToDo;
import ui.Ui;

public class TodoCommand extends Command {
    private final String desc;

    public TodoCommand(String desc) throws InvalidCommandFormatException {
        String trimmed = desc.trim();
        if (trimmed.toLowerCase().startsWith("todo")) {
            trimmed = trimmed.substring(4).trim();
        }
        if (trimmed.isEmpty()) {
            throw new InvalidCommandFormatException("You forgot to describe what your tood is...");
        }
        this.desc = trimmed;

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JerryException {
        ToDo todo = new ToDo(desc);
        this.response = taskList.addTask(todo);
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
