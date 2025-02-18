package chatbot.checkduplicates;

import chatbot.tasks.Event;
import chatbot.tasks.TaskList;

/**
 * Utility class to validation for duplicate events in the task list.
 */
public class DuplicateEventChecker {

    /**
     * Checks if an event already exists in the task list.
     *
     * @param taskList The list of tasks.
     * @param event    The event task to validation for duplication.
     * @return true if the event already exists, false otherwise.
     */
    public static boolean isDuplicate(TaskList taskList, Event event) {
        return taskList.getTasks().stream()
                .filter(existingTask -> existingTask instanceof Event)
                .map(existingTask -> (Event) existingTask)
                .anyMatch(existingEvent ->
                        existingEvent.getDescription().equalsIgnoreCase(event.getDescription()) &&
                                existingEvent.getFrom().equals(event.getFrom()) &&
                                existingEvent.getTo().equals(event.getTo())
                );
    }
}
