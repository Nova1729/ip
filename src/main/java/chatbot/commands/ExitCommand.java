package chatbot.commands;

import chatbot.Storage;
import chatbot.tasks.TaskList;

/**
 * Represents a command to exit the chatbot application.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
