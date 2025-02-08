package chatbot.commands;

import chatbot.Ui;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        int index = CheckMark.validate(input, tasks); // Delegate validation

        Task task = tasks.get(index - 1);
        task.markAsDone();
        storage.save(tasks.getTasks());

        return "Nice! I've marked this task as done:\n  " + task;
    }
}


