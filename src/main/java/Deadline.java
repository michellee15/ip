public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String desc, String dueDate) {
        super(desc);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[DUE]" + super.toString() + " (by: " + this.dueDate + ")";
    }

}

