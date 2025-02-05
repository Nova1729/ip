package chatbot.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description);
        if (isDone) {
            super.markAsDone();
        } else {
            super.markAsNotDone();
        }

    }

    @Override
    public String toFileFormat() {
        return "T | " + (this.isDone() ? "1" : "0") + " | " + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + this.getDescription();
    }
}
