package task;

import exceptions.InvalidCommandFormatException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Events extends Task {
    private final LocalDate fromDate;
    private final LocalTime fromTime;
    private final LocalDate toDate;
    private final LocalTime toTime;
    private static final DateTimeFormatter FILE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public Events(String desc, String fromDate, String fromTime, String toDate, String toTime) throws InvalidCommandFormatException {
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

    private static LocalTime parseTime(String input) throws DateTimeParseException {
        input = input.toLowerCase().replaceAll("\\s+", "");

        try {
            return LocalTime.parse(input, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            boolean isPm = input.endsWith("pm");
            boolean isAm = input.endsWith("am");
            String numberPart = input.replaceAll("(am|pm)", "");
            String[] parts = numberPart.split(":");
            int hour = Integer.parseInt(parts[0]);
            int min = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
            if (isPm && hour < 12) hour += 12;
            if (isAm && hour == 12) hour = 0;
            return LocalTime.of(hour, min);
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