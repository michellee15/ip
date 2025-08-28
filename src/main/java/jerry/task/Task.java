package jerry.task;

import jerry.exceptions.InvalidCommandFormatException;

public abstract class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) throws InvalidCommandFormatException {
        if (desc == null || desc.trim().isEmpty()) {
            throw new InvalidCommandFormatException("Oops! Task description cannot be empty...");
        }
        this.desc = desc;
        this.isDone = false;
    }

    public String getStatus() {
        return this.isDone ? "X" : " ";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String toFileString() {
        return (this.isDone ? "1" : "0") + " | " + this.desc;
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.desc;
    }
}