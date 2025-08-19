public class Tasks {
    protected String desc;
    protected boolean isDone;

    public Tasks(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public String getStatus() {
        return this.isDone ? "X" : " ";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.desc;
    }
}
