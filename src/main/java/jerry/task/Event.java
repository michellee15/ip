package jerry.task;

import jerry.exceptions.InvalidCommandFormatException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task in the Jerry application.
 * <p>
 * An Event is a type of Task that has specific
 * time period with defined start and end dates and times.
 * The class validates the input date and time strings and
 * formats them appropriately for both file storage and user-friendly display.
 */
public class Event extends Task {
    private final LocalDate fromDate;
    private final LocalTime fromTime;
    private final LocalDate toDate;
    private final LocalTime toTime;
    private static final DateTimeFormatter FILE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Constructs an Event task with a description, start date/time, and end date/time.
     *
     * @param desc the description of the event.
     * @param fromDate the start date in the format "yyyy-MM-dd".
     * @param fromTime the start time in the format "HH:mm".
     * @param toDate the end date in the format "yyyy-MM-dd".
     * @param toTime the end time in the format "HH:mm".
     * @throws InvalidCommandFormatException if any date or time string is invalid or incorrectly formatted.
     */
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