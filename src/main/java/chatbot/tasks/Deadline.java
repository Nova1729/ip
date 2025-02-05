package chatbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by); // Parse string to LocalDate
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description);

        if (isDone) {
            super.markAsDone();
        } else {
            super.markAsNotDone();
        }

        this.by = LocalDate.parse(by); // Parse string to LocalDate
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toFileFormat() {
        return "D | " + (this.isDone() ? "1" : "0") + " | " + this.getDescription() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] "
                + this.getDescription() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}




