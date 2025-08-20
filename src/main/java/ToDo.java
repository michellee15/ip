public class ToDo extends Task {

    public ToDo(String desc) throws InvalidCommandFormatException {
        super(desc);
        String[] entries = desc.split(" ",2);
        if (entries[1].isEmpty()) {
            throw new InvalidCommandFormatException("Uh Oh! You forgot to describe what your todo is...");
        }
    }

    @Override
    public String toString() {
        return "[TODO]" + super.toString();
    }
}
