package chatbot.commands;

import chatbot.tasks.*;
import chatbot.Ui;
import chatbot.Storage;

/**
 * Represents a command to list all tasks currently stored in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param tasks   The {@link TaskList} containing the user's tasks.
     * @param ui      The {@link Ui} responsible for user interaction.
     * @param storage The {@link Storage} that manages saving and loading of tasks (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessage((i + 1) + ". " + tasks.get(i));
        }
    }
}


