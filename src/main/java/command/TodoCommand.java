package command;

import exceptions.InvalidCommandFormatException;
import exceptions.JerryException;
import storage.Storage;
import tasklist.TaskList;
import task.ToDo;
import ui.Ui;

public class TodoCommand extends Command {
    private String desc = "";

    public TodoCommand(String desc) throws InvalidCommandFormatException {
        try {
            String[] strArray = desc.split("/from");
            this.desc = strArray[0].trim();
        } catch (Exception e) {
            throw new InvalidCommandFormatException("Event must have time in 'from-to' format");
        }
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
