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
     * @param todos The list of added todo tasks.
     * @param tasks The current task list to get the updated count.
     * @return A formatted string response.
     */
    public static String generate(List<Todo> todos, TaskList tasks) {
        assert todos != null : "Todo list should not be null";
        assert tasks != null : "TaskList should not be null";

        StringBuilder response = new StringBuilder("Got it. I've added these tasks:\n");
        for (Todo todo : todos) {
            response.append("  ").append(todo).append("\n");
        }
        response.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
        return response.toString().trim();
    }
}
