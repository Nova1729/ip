package chatbot.commands;

import chatbot.Storage;
import chatbot.Ui;
import chatbot.tasks.TaskList;

/**
 * Represents a command to exit the chatbot application.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
