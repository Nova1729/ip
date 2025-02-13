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
     * @param addedEvents     The list of newly added event tasks.
     * @param duplicateEvents The list of events that were duplicates and not added.
     * @param tasks           The current task list.
     * @return A formatted response message.
     */
    public static String generateResponse(List<Event> addedEvents, List<Event> duplicateEvents, TaskList tasks) {
        assert addedEvents != null : "Event list should not be null";
        assert duplicateEvents != null : "Duplicate event list should not be null";
        assert tasks != null : "Task list should not be null";

        StringBuilder response = new StringBuilder();

        if (!addedEvents.isEmpty()) {
            response.append("Got it. I've added these tasks:\n");
            for (Event event : addedEvents) {
                response.append("  ").append(event).append("\n");
            }
        }

        if (!duplicateEvents.isEmpty()) {
            response.append("\nThese events were **not added** because they are duplicates:\n");
            for (Event event : duplicateEvents) {
                response.append("  ").append(event).append("\n");
            }
        }

        response.append("\nNow you have ").append(tasks.size()).append(" tasks in the list.");
        return response.toString().trim();
    }
}

