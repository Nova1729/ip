package chatbot.commands;

import chatbot.Storage;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;

import java.util.List;
import java.util.stream.Collectors;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.getTasks().stream()
                .filter(task -> task.getDescription().contains(keyword))
                .toList();

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        }

        return matchingTasks.stream()
                .map(task -> (matchingTasks.indexOf(task) + 1) + ". " + task)
                .collect(Collectors.joining("\n", "Here are the matching tasks in your list:\n", ""));
    }
}
