package chatbot.checkduplicates;

import chatbot.tasks.Deadline;
import chatbot.tasks.TaskList;

/**
 * Utility class to check for duplicate deadlines in the task list.
 */
public class DuplicateDeadlineChecker {

    /**
     * Checks if a deadline already exists in the task list.
     *
     * @param taskList The list of tasks.
     * @param deadline The deadline task to check for duplication.
     * @return true if the deadline already exists, false otherwise.
     */
    public static boolean isDuplicate(TaskList taskList, Deadline deadline) {
        return taskList.getTasks().stream()
                .filter(task -> task instanceof Deadline) // Ensure we are only checking Deadline tasks
                .map(task -> (Deadline) task) // Cast Task to Deadline
                .anyMatch(existingDeadline ->
                        existingDeadline.getDescription().equalsIgnoreCase(deadline.getDescription()) &&
                                existingDeadline.getBy().equals(deadline.getBy()));
    }
}
