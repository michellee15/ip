public class ToDo extends Task {

    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[TODO]" + super.toString();
    }
}
