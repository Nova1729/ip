package chatbot.checkduplicates;

import chatbot.tasks.TaskList;
import chatbot.tasks.Todo;

/**
 * Utility class to check for duplicate todo tasks in the task list.
 */
public class DuplicateTodoChecker {

    /**
     * Checks if a todo task already exists in the task list.
     *
     * @param taskList The list of tasks.
     * @param todo The todo task to check for duplication.
     * @return true if the todo already exists, false otherwise.
     */
    public static boolean isDuplicate(TaskList taskList, Todo todo) {
        return taskList.getTasks().stream()
                .filter(task -> task instanceof Todo)
                .map(task -> (Todo) task)
                .anyMatch(existingTodo -> existingTodo.getDescription().equalsIgnoreCase(todo.getDescription()));
    }
}
