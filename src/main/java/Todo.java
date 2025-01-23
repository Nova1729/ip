public class Todo extends Task {
    public Todo(String description) {
        super(description); // inheritance
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}
