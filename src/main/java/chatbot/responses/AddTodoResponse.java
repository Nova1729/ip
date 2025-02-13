package chatbot.responses;

import chatbot.tasks.TaskList;
import chatbot.tasks.Todo;

import java.util.List;

/**
 * Generates the response for adding multiple todo tasks.
 */
public class AddTodoResponse {

    /**
     * Generates a response message for the added todo tasks.
     *
     * @param todos The list of newly added todo tasks.
     * @param duplicateTodos The list of duplicate todo tasks.
     * @param tasks The current task list to get the updated count.
     * @return A formatted string response.
     */
    public static String generate(List<Todo> todos, List<Todo> duplicateTodos, TaskList tasks) {
        assert todos != null : "Todo list should not be null";
        assert duplicateTodos != null : "Duplicate todo list should not be null";
        assert tasks != null : "TaskList should not be null";

        StringBuilder response = new StringBuilder();

        if (!todos.isEmpty()) {
            response.append("Got it. I've added these tasks:\n");
            for (Todo todo : todos) {
                response.append("  ").append(todo).append("\n");
            }
            response.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
        }

        if (!duplicateTodos.isEmpty()) {
            response.append("\nThese todos were NOT added because they are duplicates:\n");
            for (Todo duplicate : duplicateTodos) {
                response.append("  ").append(duplicate).append("\n");
            }
        }

        return response.toString().trim();
    }
}

