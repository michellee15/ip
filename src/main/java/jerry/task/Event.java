package jerry.task;

import jerry.exceptions.InvalidCommandFormatException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Event extends Task {
    private final LocalDate fromDate;
    private final LocalTime fromTime;
    private final LocalDate toDate;
    private final LocalTime toTime;
    private static final DateTimeFormatter FILE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public Event(String desc, String fromDate, String fromTime, String toDate, String toTime) throws InvalidCommandFormatException {
        super(desc);
        try {
            this.fromDate = LocalDate.parse(fromDate, FILE_DATE_FORMATTER);
            this.fromTime = LocalTime.parse(fromTime, TIME_FORMATTER);
            this.toDate = LocalDate.parse(toDate, FILE_DATE_FORMATTER);
            this.toTime = LocalTime.parse(toTime, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Invalid date format! Expected format: yyyy-MM-dd, e.g., 2022-08-06");
        }
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | "
                + fromDate.toString() + " " + fromTime.toString() + " | "
                + toDate.toString() + " " + toTime.toString();
    }

    @Override
    public String toString() {
        return "[EVENT]" + super.toString() + " (from: "
                + this.fromDate.format(DISPLAY_DATE_FORMATTER) + " " + this.fromTime.format(TIME_FORMATTER)
                + " to: " + this.toDate.format(DISPLAY_DATE_FORMATTER) + " " + this.fromTime.format(TIME_FORMATTER) + ")";
    }
}