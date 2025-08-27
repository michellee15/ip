package command;

import exceptions.JerryException;
import storage.Storage;
import tasklist.TaskList;

public abstract class Command {
    protected String response;

    public abstract void execute(TaskList tasks, Storage storage) throws JerryException;

    public abstract boolean isExit();

    public abstract String getString();
}
