package command;

import exceptions.InvalidCommandFormatException;
import exceptions.JerryException;

import task.Event;
import tasklist.TaskList;
import storage.Storage;
import ui.Ui;

public class EventCommand extends Command {

    private String desc = "";
    private String fromDate = "";
    private String fromTime = "";
    private String toDate  = "";
    private String toTime = "";

    public EventCommand(String desc) throws InvalidCommandFormatException {
        try {
            String[] strArray = desc.split("/from");
            this.desc = strArray[0].trim();
            String[] dateData = strArray[1].split("to");
            String from = dateData[0];
            String to = dateData[1];
            String[] fromDateTime = from.trim().split(" ");
            String[] toDateTime = to.trim().split(" ");
            this.fromDate = fromDateTime[0].trim();
            this.fromTime = fromDateTime[1].trim();
            this.toDate  = toDateTime[0].trim();
            this.toTime = toDateTime[1].trim();
        } catch (Exception e) {
            throw new InvalidCommandFormatException("Event must have time in 'from-to' format");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JerryException {
        Event event = new Event(desc, fromDate, fromTime, toDate, toTime);
        this.response = taskList.addTask(event);
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
