package command;

import exceptions.InvalidCommandFormatException;
import task.ToDo;

public class TodoCommand extends Command{
    private String desc = "";
    public TodoCommand(String desc) throws InvalidCommandFormatException {
        try {
            String[] strArray = desc.split("/from");
            this.desc = strArray[0].trim();
        } catch (Exception e) {
            throw new InvalidCommandFormatException("Event must have time in 'from-to' format");
        }
    }
    public ToDo run() throws InvalidCommandFormatException {
        return new ToDo(desc);
    }
}
