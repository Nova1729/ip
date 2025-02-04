package chatbot.tasks;

/**
 * Represents a task with a description and a completion status.
 */
public class Task {
    /** The description of the task. */
    public String description;

    /** The completion status of the task. True if completed, false otherwise. */
    public boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is initially not marked as done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "X" indicates the task is done, and " " (space) indicates it is not done.
     *
     * @return A string representing the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Converts the task into a file-friendly format for saving.
     * The format follows: "T | {status} | {description}".
     *
     * @return A string representation of the task in file format.
     */
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the task.
     * The format follows: "[{status}] {description}".
     *
     * @return A string describing the task with its status.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}



