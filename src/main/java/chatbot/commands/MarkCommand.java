package chatbot.commands;

import chatbot.tasks.*;
import chatbot.Ui;
import chatbot.Storage;

/**
 * Represents a command to mark a specific task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a {@code MarkCommand} with the specified task index.
     *
     * @param index The index of the task to be marked as done (1-based index).
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command by marking the specified task as done.
     *
     * @param tasks   The {@link TaskList} containing the user's tasks.
     * @param ui      The {@link Ui} responsible for user interaction.
     * @param storage The {@link Storage} that manages saving and loading of tasks.
     * @throws IndexOutOfBoundsException If the task index is out of valid range.
     * @throws Exception If an unexpected error occurs while marking the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task number.");
        }
        Task task = tasks.get(index - 1);
        task.markAsDone();
        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage("  " + task);
        storage.save(tasks.getTasks());
    }
}


