import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Events extends Task {
    private final LocalDate date;
    private final LocalTime from;
    private final LocalTime to;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public Events(String desc) throws InvalidCommandFormatException {
        super(parseDesc(desc));
        int fromIdx = desc.lastIndexOf(" from ");
        int toIdx = desc.lastIndexOf(" to ");
        if (fromIdx == -1 || toIdx == -1 || fromIdx >= toIdx) {
            throw new InvalidCommandFormatException("Event must have time in 'from-to' format");
        }
        String fromString = desc.substring(fromIdx + 6, toIdx).trim();
        String toString = desc.substring(toIdx + 4).trim();
        String dateString = desc.substring(super.getDesc().length(), fromIdx);
        try {
            this.date = (dateString.isEmpty()) ? null : LocalDate.parse(dateString, DATE_FORMATTER);
            this.from = LocalTime.parse(fromString, TIME_FORMATTER);
            this.to = LocalTime.parse(toString, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Invalid date or time format. Expected format: yyyy-MM-dd HH:mm");
        }

    }

    private static String parseDesc(String input) throws InvalidCommandFormatException {
        int fromIdx =  input.lastIndexOf(" from ");
        String desc = (fromIdx == -1) ? input.trim() : input.substring(0, fromIdx).trim();
        if (desc.isEmpty()) {
            throw new InvalidCommandFormatException("You must provide a description for the event!");
        }
        return desc;
    }

    @Override
    public String toFileString() {
        String datePart = (this.date == null) ? " " : this.date.format(DATE_FORMATTER);
        return "E | " + super.toFileString() + " | "
                + datePart + " "
                + this.from.format(TIME_FORMATTER) + "-" + this.to.format(TIME_FORMATTER);

    }

    @Override
    public String toString() {
        String datePart = (this.date == null) ? " " : this.date.format(DATE_FORMATTER);
        return "[EVENT]" + super.toString() + "(" + datePart
                + " from: " + this.from.format(TIME_FORMATTER) + " to: "
                + this.to.format(TIME_FORMATTER) + ")";
    }
}