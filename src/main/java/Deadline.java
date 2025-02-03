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
        this.isDone = isDone;
        this.by = LocalDate.parse(by); // Parse string to LocalDate
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}




