public class Deadline extends Task {
    private final String dueDate;

    public Deadline(String desc) throws InvalidCommandFormatException {
//        String[] entries = desc.split(" ",2);
//        if (entries.length < 2 || entries[1].trim().isEmpty()) {
//            throw new InvalidCommandFormatException("You need to specify the task description and the due date");
//        }
       super(parseDesc(desc));
       this.dueDate = parseDate(desc);

    }

    private static String parseDesc(String desc) throws InvalidCommandFormatException {
        if (desc.toLowerCase().startsWith("deadline ")) {
            desc = desc.substring(9).trim();
        }
        if (!desc.contains("by")) {
            throw new InvalidCommandFormatException("Deadline must have 'by' keyword followed by due date");
        }
        String taskDesc = desc.substring(0, desc.indexOf("by")).trim();
        if (taskDesc.isEmpty()) {
            throw new InvalidCommandFormatException("Uh Oh! You forgot to describe your task and due date!");
        }
        return taskDesc;
    }

    private static String parseDate(String desc) throws InvalidCommandFormatException {
        if (!desc.contains("by")) throw new InvalidCommandFormatException("Uh Oh! Deadline must have a due date or time");
        String by = desc.substring(desc.indexOf("by") + 3).trim();
        if (by.isEmpty()) throw new InvalidCommandFormatException("The due date or time cannot be empty!");
        return by;
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + this.dueDate;
    }

    @Override
    public String toString() {
        return "[DUE]" + super.toString() + " (by: " + this.dueDate + ")";
    }

}