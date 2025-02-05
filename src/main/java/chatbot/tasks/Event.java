package chatbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a description, start date, and end date.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an {@code Event} task.
     *
     * @param description The event description.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from); // Parse string to LocalDate
        this.to = LocalDate.parse(to);     // Parse string to LocalDate
    }

    /**
     * Constructs an {@code Event} task with completion status.
     *
     * @param description The event description.
     * @param from The start date of the event.
     * @param to The end date of the event.
     * @param isDone Whether the event task is completed.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        if (isDone) {
            super.markAsDone();
        } else {
            super.markAsNotDone();
        }
        this.from = LocalDate.parse(from); // Parse string to LocalDate
        this.to = LocalDate.parse(to);     // Parse string to LocalDate
    }

    /**
     * Gets the start date of the event.
     *
     * @return The start date.
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Gets the end date of the event.
     *
     * @return The end date.
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Converts the event into a file-friendly format for saving.
     *
     * @return A string representation of the event in file format.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (this.isDone() ? "1" : "0") + " | " + this.getDescription() + " | "
                + from + " | " + to;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return A formatted string with event details.
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + this.getDescription()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}






