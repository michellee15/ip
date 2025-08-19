public class Events extends Task {
    String from;
    String to;

    public Events(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[EVENT]" + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
    }
}
