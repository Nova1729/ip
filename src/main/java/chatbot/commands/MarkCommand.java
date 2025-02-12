package chatbot.commands;

import chatbot.Storage;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.check.CheckMark;

/**
 * Represents a command to mark a specific task as done.
 */
public class MarkCommand extends Command {
    /** The raw input string that should contain a numeric task index. */
    private final String input;

    /**
     * Constructs a {@code MarkCommand} with the specified input.
     *
     * @param input The raw input that should contain the index of the task to be marked.
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws Exception {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage instance cannot be null";

        int index = CheckMark.validate(input, tasks);
        assert index > 0 && index <= tasks.size() : "Index must be within valid range";

        Task task = tasks.get(index - 1);
        assert task != null : "Task at index " + index + " should not be null";

        task.markAsDone();
        storage.save(tasks.getTasks());

        return "Nice! I've marked this task as done:\n  " + task;
    }
}


