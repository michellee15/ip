package jerry.command;

import jerry.exceptions.InvalidCommandFormatException;
import jerry.exceptions.JerryException;

import jerry.task.Event;
import jerry.storage.Storage;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

/**
 * Represents a command to add an event task in the application
 * <p>
 * It validates the user input format to ensure that it has a description
 * and that the start and end date-times are correctly specified using
 * the '/from' and 'to' keywords.
 * <p>
 * The event is added to the task list, saved to storage and a display will be shown
 * as a saved confirmation to the user
 */
public class EventCommand extends Command {

    private final String desc;
    private final String fromDate;
    private final String fromTime;
    private final String toDate;
    private final String toTime;

    /**
     * The input must include an 'event' command keyword, description, followed by '/from' keyword
     * with start date and time, and the 'to' keyword with the end date and time.
     * If the format is invalid, an exception is thrown.
     * @param desc user input string to be parsed.
     * @throws InvalidCommandFormatException if the user input format is incorrect.
     */
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

    /**
     * Parses the input string into event description and date-time portion.
     * <p>
     * It ensures that the description isnot empty, and that both '/from' and
     * 'to' keywords are present in the input.
     * @param desc the full user input string.
     * @return an array with the description at index 0 and date-time string at index 1.
     * @throws InvalidCommandFormatException if the description is empty or required keywords are missing.
     */
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
