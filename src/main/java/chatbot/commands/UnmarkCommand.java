package chatbot.commands;

import chatbot.Storage;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.check.CheckUnmark;

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
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws Exception {
        int index = CheckUnmark.validate(input, tasks); // Delegate validation

        Task task = tasks.get(index - 1);
        task.markAsNotDone();
        storage.save(tasks.getTasks());

        return "OK, I've marked this task as not done yet:\n  " + task;
    }
}




