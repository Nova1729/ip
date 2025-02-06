package chatbot.commands;

import chatbot.Storage;
import chatbot.Ui;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;

import java.util.ArrayList;

/**
 * Represents a command to find tasks that contain a specified keyword in their description.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that match the keyword
     * and displaying them to the user.
     *
     * @param tasks   The {@link TaskList} containing all the tasks.
     * @param ui      The {@link Ui} responsible for user interaction.
     * @param storage The {@link Storage} that manages saving and loading of tasks (not used in this command).
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        ui.showMessage("____________________________________________________________");
        if (matchingTasks.isEmpty()) {
            ui.showMessage(" No matching tasks found.");
        } else {
            ui.showMessage(" Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage(" " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
        ui.showMessage("____________________________________________________________");
        return null;
    }
}


