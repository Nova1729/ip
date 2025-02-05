package chatbot.commands;

import chatbot.Storage;
import chatbot.Ui;
import chatbot.tasks.TaskList;

/**
 * Represents an abstract command to be executed.
 * This is the base class for all specific command implementations in the chatbot.
 */
public abstract class Command {

    /**
     * Executes the specific command logic.
     *
     * @param tasks   The {@link TaskList} containing the current list of tasks.
     * @param ui      The {@link Ui} instance to handle user interactions.
     * @param storage The {@link Storage} instance to handle saving/loading tasks from storage.
     * @throws Exception If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Determines if the command is an exit command.
     * Exit commands terminate the chatbot.
     *
     * @return {@code true} if the command is an exit command, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}



