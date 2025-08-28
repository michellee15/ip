package jerry.command;

import jerry.exceptions.JerryException;

import jerry.storage.Storage;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

public abstract class Command {
    protected String response;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JerryException;

    public abstract boolean isExit();

    public abstract String getString();
}
