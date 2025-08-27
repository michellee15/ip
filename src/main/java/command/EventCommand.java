package command;

import exceptions.InvalidCommandFormatException;
import task.Event;

public class EventCommand extends Command{
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
    public Event run() throws InvalidCommandFormatException {
        return new Event(desc, fromDate, fromTime, toDate, toTime);
    }
}
