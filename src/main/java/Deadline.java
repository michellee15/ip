public class Deadline extends Task {
    private final String dueDate;

    public Deadline(String desc) throws InvalidCommandFormatException {
        String[] entries = desc.split(" ",2);
        if (entries.length < 2 || entries[1].trim().isEmpty()) {
            throw new InvalidCommandFormatException("You need to specify the task description and the due date");
        }
        String[] parts = entries[1].split("by", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new InvalidCommandFormatException("You must add a keyword 'by' after the task description and before the due date/time entered");
        }
        super(parts[0].trim());
        this.dueDate = parts[1].trim();

    }

    @Override
    public String toString() {
        return "[DUE]" + super.toString() + " (by: " + this.dueDate + ")";
    }

}

