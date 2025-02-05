package chatbot.commands;

import chatbot.Storage;
import chatbot.Ui;
import chatbot.tasks.TaskList;

/**
 * Represents a command to exit the chatbot application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying a farewell message.
     *
     * @param tasks   The {@link TaskList} containing the tasks (not used in this command).
     * @param ui      The {@link Ui} responsible for user interaction.
     * @param storage The {@link Storage} that manages saving and loading of tasks (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates that this command will terminate the chatbot program.
     *
     * @return {@code true}, indicating that the program should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}


