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
     * @param deadlines The list of added deadlines.
     * @param taskCount The total number of tasks in the task list after addition.
     * @return The formatted response message.
     */
    public static String generate(List<Deadline> deadlines, int taskCount) {
        assert deadlines != null : "Deadlines list cannot be null";
        assert taskCount >= 0 : "Task count cannot be negative";

        StringBuilder response = new StringBuilder("Got it. I've added these tasks:\n");
        for (Deadline deadline : deadlines) {
            response.append("  ").append(deadline).append("\n");
        }
        response.append("Now you have ").append(taskCount).append(" tasks in the list.");

        return response.toString().trim();
    }
}

