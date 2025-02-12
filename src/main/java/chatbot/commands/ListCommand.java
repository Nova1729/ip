package chatbot.commands;

import chatbot.Storage;
import chatbot.tasks.TaskList;

/**
 * Represents a command to list all tasks currently stored in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param tasks   The {@link TaskList} containing the user's tasks.
     * @param storage The {@link Storage} that manages saving and loading of tasks (not used in this command).
     * @return
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return response.toString().trim(); // Return the response string
    }
}


