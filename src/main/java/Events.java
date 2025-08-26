import java.util.Arrays;

public class Events extends Task {
    private final String date;
    private final String from;
    private final String to;

    public Events(String desc) throws InvalidCommandFormatException {
        super(parseDesc(desc));
        int fromIdx = desc.lastIndexOf(" from ");
        int toIdx = desc.lastIndexOf(" to ");
        if (fromIdx == -1 || toIdx == -1 || fromIdx >= toIdx) {
            throw new InvalidCommandFormatException("Event must have time in 'from-to' format");
        }
        this.from = desc.substring(fromIdx + 6, toIdx).trim();
        this.to = desc.substring(toIdx + 4).trim();
        this.date = desc.substring(super.getDesc().length(), fromIdx);
        if (this.from.isEmpty() || this.to.isEmpty()) {
            throw new InvalidCommandFormatException("Event must have valid from and to times");
        }
    }

    private static String parseDesc(String input) throws InvalidCommandFormatException {
        int fromIdx =  input.lastIndexOf(" from ");
        String desc = (fromIdx == -1) ? input.trim() : input.substring(0, fromIdx).trim();
        if (desc.isEmpty()) throw new InvalidCommandFormatException("You must provide a description for the event!");
        return desc;
    }

    @Override
    public String toFileString() {
        if (date.isEmpty()) {
            return "E | " + super.toFileString() + " | " + this.from + "-" + this.to;
        } else {
            return "E | " + super.toFileString() + " | " + this.date + " " + this.from + "-" + this.to;
        }
    }

    @Override
    public String toString() {
        if (date.isEmpty()) {
            return "[EVENT]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
        } else {
            return "[EVENT]" + super.toString() + "(" + this.date + " from: " + this.from + " to: " + this.to + ")";
        }
    }
}