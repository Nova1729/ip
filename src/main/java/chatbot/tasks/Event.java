package chatbot.tasks;

/**
 * Represents an event task with a description, start time, and end time.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructs an {@code Event} task.
     *
     * @param description The event description.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from; // Store the input as-is
        this.to = to;     // Store the input as-is
    }

    /**
     * Constructs an {@code Event} task with completion status.
     *
     * @param description The event description.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @param isDone Whether the event task is completed.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        if (isDone) {
            super.markAsDone();
        } else {
            super.markAsNotDone();
        }
        this.from = from; // Store the input as-is
        this.to = to;     // Store the input as-is
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time.
     */
    public String getTo() {
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
                + " (from: " + from + " to: " + to + ")";
    }
}






