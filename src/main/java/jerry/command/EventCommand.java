package jerry.command;

import jerry.exceptions.InvalidCommandFormatException;
import jerry.exceptions.JerryException;

import jerry.task.Event;
import jerry.storage.Storage;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

public class EventCommand extends Command {

    private final String desc;
    private final String fromDate;
    private final String fromTime;
    private final String toDate;
    private final String toTime;

    public EventCommand(String desc) throws InvalidCommandFormatException {
        String[] strArray = getStrings(desc);
        this.desc = strArray[0].trim();
        String[] dateData = strArray[1].split("to");
        String from = dateData[0];
        String to = dateData[1];
        String[] fromDateTime = from.trim().split(" ");
        String[] toDateTime = to.trim().split(" ");
        if (fromDateTime.length != 2 || toDateTime.length != 2) {
            throw new InvalidCommandFormatException(
                    "Invalid date/time format. Expected: yyyy-MM-dd HH:mm (e.g., 2025-08-27 12:30)");
        }
        this.fromDate = fromDateTime[0].trim();
        this.fromTime = fromDateTime[1].trim();
        this.toDate  = toDateTime[0].trim();
        this.toTime = toDateTime[1].trim();
    }

    private static String[] getStrings(String desc) throws InvalidCommandFormatException {
        String trimmed = desc.trim();
        if (trimmed.toLowerCase().startsWith("event")) {
            trimmed = trimmed.substring(5).trim();
        }
        if (trimmed.isEmpty() || trimmed.startsWith("/from")) {
            throw new InvalidCommandFormatException("Event description cannot be empty...");
        }
        String[] strArray = trimmed.split("/from");
        if (strArray.length < 2) {
            throw new InvalidCommandFormatException("Event must have '/from' and 'to' keywords.");
        }
        return strArray;
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
