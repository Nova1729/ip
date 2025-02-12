package chatbot.commands;

import chatbot.Storage;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;

import java.util.ArrayList;

/**
 * Represents a command to find tasks that contain a specified keyword in their description.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        assert keyword != null && !keyword.trim().isEmpty() : "FindCommand keyword cannot be null or empty";

        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null : "TaskList cannot be null";

        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        }
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            response.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
        }

        assert !response.isEmpty() : "Response should not be empty if matches are found";
        return response.toString().trim();
    }
}



