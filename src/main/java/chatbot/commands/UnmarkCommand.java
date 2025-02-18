package chatbot.commands;

import chatbot.Storage;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.validation.UnmarkValidator;

/**
 * Represents a command to unmark a specific task as not done.
 */
public class UnmarkCommand extends Command {
    /** The raw input string that should contain a numeric task index. */
    private final String input;

    /**
     * Constructs an {@code UnmarkCommand} with the specified input.
     *
     * @param input The raw input that should contain the index of the task to be unmarked.
     */
    public UnmarkCommand(String input) {
        assert input != null && !input.trim().isEmpty() : "Input for UnmarkCommand cannot be null or empty";

        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws Exception {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage instance cannot be null";

        int index = UnmarkValidator.validate(input, tasks);
        assert index > 0 && index <= tasks.size() : "Index must be within valid range";

        Task task = tasks.get(index - 1);
        assert task != null : "Task at index " + index + " should not be null";

        task.markAsNotDone();
        storage.save(tasks.getTasks());

        return "OK, I've marked this task as not done yet:\n  " + task;
    }
}




