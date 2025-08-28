package jerry.task;

import jerry.exceptions.InvalidCommandFormatException;

/**
 * Represents a generic task in the Jerry application
 * <p>
 * This class serves as the parent class for specific task types, provides methods to
 * mark and unmark tasks, retrieve current status and convert tasks to string representation
 * for display or storage.
 */
public abstract class Task {
    protected String desc;
    protected boolean isDone;

    /**
     * Constructs a task with given string description.
     *
     * @param desc description of task.
     * @throws InvalidCommandFormatException if the description is null or empty.
     */
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

    /**
     * Display a string representation of the task for saving to file.
     *
     * @return a string containing completion status, description which are separated by '|'.
     */
    public String toFileString() {
        return (this.isDone ? "1" : "0") + " | " + this.desc;
    }

    /**
     * Display a human-readable string representation of the task.
     *
     * @return a string containing completion status and task description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.desc;
    }
}