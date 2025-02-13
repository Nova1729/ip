package chatbot.responses;

import chatbot.tasks.Deadline;
import java.util.List;

/**
 * Utility class to generate responses for the AddDeadlineCommand.
 */
public class AddDeadlineResponse {

    /**
     * Generates the response message for adding multiple deadline tasks.
     *
     * @param addedDeadlines The list of successfully added deadlines.
     * @param duplicateDeadlines The list of duplicate deadlines that were not added.
     * @param taskCount The total number of tasks in the task list after addition.
     * @return The formatted response message.
     */
    public static String generate(List<Deadline> addedDeadlines, List<Deadline> duplicateDeadlines, int taskCount) {
        assert addedDeadlines != null : "Added deadlines list cannot be null";
        assert duplicateDeadlines != null : "Duplicate deadlines list cannot be null";
        assert taskCount >= 0 : "Task count cannot be negative";

        StringBuilder response = new StringBuilder();

        if (!addedDeadlines.isEmpty()) {
            response.append("Got it. I've added these tasks:\n");
            for (Deadline deadline : addedDeadlines) {
                response.append("  ").append(deadline).append("\n");
            }
        }

        if (!duplicateDeadlines.isEmpty()) {
            response.append("\nThe following deadlines were **not added** because they are duplicates:\n");
            for (Deadline deadline : duplicateDeadlines) {
                response.append("  ").append(deadline).append("\n");
            }
        }

        response.append("\nNow you have ").append(taskCount).append(" tasks in the list.");

        return response.toString().trim();
    }
}

