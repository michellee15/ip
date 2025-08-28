package jerry.task;

import jerry.exceptions.InvalidCommandFormatException;

public class ToDo extends Task {

    public ToDo(String desc) throws InvalidCommandFormatException {
        super(parseDesc(desc));
    }

    private static String parseDesc(String input) throws InvalidCommandFormatException {
        String desc = input.trim();
        if (desc.toLowerCase().startsWith("todo ")) {
            desc = desc.substring(5).trim();
        }
        if (desc.isEmpty() || desc.equalsIgnoreCase("todo")) {
            throw new InvalidCommandFormatException("You forgot to describe what your todo is...");
        }
        return desc;
    }

    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }

    @Override
    public String toString() {
        return "[TODO]" + super.toString();
    }
}