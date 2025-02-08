package chatbot.commands;

import chatbot.Storage;
import chatbot.Ui;
import chatbot.exceptions.DeleteException;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.check.CheckDelete;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    /** The raw input string that should contain a numeric task index. */
    private final String input;

    /**
     * Constructs a {@code DeleteCommand} with the specified input.
     *
     * @param input The raw input that should contain the index of the task to be deleted.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the delete command by removing the task from the task list,
     * generating a confirmation message, and updating the storage.
     *
     * @param tasks   The {@link TaskList} containing the tasks.
     * @param ui      The {@link Ui} instance to handle user interactions.
     * @param storage The {@link Storage} that manages saving and loading of tasks.
     * @return A string response confirming the task has been deleted.
     * @throws DeleteException If the input is not numeric or the index is out of range.
     * @throws Exception       If an error occurs while saving the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        int index = CheckDelete.validate(input, tasks); // Delegate validation

        Task removedTask = tasks.remove(index - 1);
        storage.save(tasks.getTasks());

        return "Noted. I've removed this task:\n  " + removedTask + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}


