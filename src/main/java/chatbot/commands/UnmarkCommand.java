package chatbot.commands;

import chatbot.tasks.*;
import chatbot.Ui;
import chatbot.Storage;

/**
 * Represents a command to unmark a specific task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an {@code UnmarkCommand} with the specified task index.
     *
     * @param index The index of the task to be unmarked as not done (1-based index).
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command by marking the specified task as not done.
     *
     * @param tasks   The {@link TaskList} containing the user's tasks.
     * @param ui      The {@link Ui} responsible for user interaction.
     * @param storage The {@link Storage} that manages saving and loading of tasks.
     * @throws IndexOutOfBoundsException If the task index is out of valid range.
     * @throws Exception If an unexpected error occurs while unmarking the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task number.");
        }
        Task task = tasks.get(index - 1);
        task.markAsNotDone();
        ui.showMessage("OK, I've marked this task as not done yet:");
        ui.showMessage("  " + task);
        storage.save(tasks.getTasks());
    }
}



