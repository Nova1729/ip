package chatbot.commands;

import chatbot.Storage;
import chatbot.Ui;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    /** The index of the task to be deleted. */
    private final int index;

    /**
     * Constructs a {@code DeleteCommand} with the specified task index.
     *
     * @param index The index of the task to be deleted (1-based index).
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task from the task list,
     * displaying a confirmation message, and updating the storage.
     *
     * @param tasks   The {@link TaskList} containing the tasks.
     * @param ui      The {@link Ui} responsible for user interaction.
     * @param storage The {@link Storage} that manages saving and loading of tasks.
     * @return
     * @throws IndexOutOfBoundsException If the index is out of range.
     * @throws Exception                 If an error occurs while saving the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task number.");
        }
        Task removedTask = tasks.remove(index - 1);
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage("  " + removedTask);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getTasks());
        return null;
    }
}



