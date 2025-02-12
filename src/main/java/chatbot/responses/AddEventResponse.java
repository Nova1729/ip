package chatbot.responses;

import chatbot.tasks.Event;
import chatbot.tasks.TaskList;
import java.util.List;

/**
 * Handles the response generation for adding event tasks.
 */
public class AddEventResponse {

    /**
     * Generates a response message after adding event tasks.
     *
     * @param events The list of added event tasks.
     * @param tasks  The current task list.
     * @return A formatted response message.
     */
    public static String generateResponse(List<Event> events, TaskList tasks) {
        assert events != null : "Event list should not be null";
        assert tasks != null : "Task list should not be null";

        StringBuilder response = new StringBuilder("Got it. I've added these tasks:\n");
        for (Event event : events) {
            response.append("  ").append(event).append("\n");
        }
        response.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
        return response.toString().trim();
    }
}

