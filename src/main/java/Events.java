public class Events extends Task {
    private final String from;
    private final String to;

    public Events(String desc) throws InvalidCommandFormatException {
        String[] entries = desc.split(" ", 2);
        if (entries.length < 2 || entries[1].trim().isEmpty()) {
            throw new InvalidCommandFormatException("Uh Oh! You need to specify the event description and a timeframe (its starting time and ending time)");
        }
        String[] events = entries[1].split("from|to");
        if (events.length < 3 || events[0].trim().isEmpty() || events[1].trim().isEmpty() || events[2].trim().isEmpty()) {
            throw new InvalidCommandFormatException("Oops! Event must have 'from' and 'to' keywords!");
        }
        super(events[0].trim());
        this.from = events[1].trim();
        this.to = events[2].trim();
    }

    @Override
    public String toString() {
        return "[EVENT]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
