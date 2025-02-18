package chatbot.commands;

import chatbot.Storage;
import chatbot.exceptions.EventException;
import chatbot.responses.AddEventResponse;
import chatbot.tasks.Event;
import chatbot.tasks.TaskList;
import chatbot.validation.EventValidator;
import chatbot.checkduplicates.DuplicateEventChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to add multiple event tasks to the task list.
 */
public class AddEventCommand extends Command {

    /** The input string containing the event descriptions and time details. */
    private final String input;

    /**
     * Constructs an {@code AddEventCommand} with the specified input.
     *
     * @param input The input string containing multiple events separated by semicolons,
     *              each in the format: "description /from start_time /to end_time".
     */
    public AddEventCommand(String input) {
        assert input != null && !input.trim().isEmpty() : "Input for AddEventCommand cannot be null or empty";

        this.input = input;
    }

    /**
     * Executes the command to add multiple event tasks to the task list.
     *
     * @param tasks   The {@link TaskList} containing the current list of tasks.
     * @param storage The {@link Storage} instance to handle saving/loading tasks from storage.
     * @return A string response confirming the event tasks have been added.
     * @throws EventException If any input does not contain the required "/from" and "/to" clauses.
     * @throws Exception      If an error occurs during saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws Exception {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage instance cannot be null";

        String[] eventParts = input.split(";");
        assert eventParts.length > 0 : "Event input should not result in an empty array";

        List<Event> addedEvents = new ArrayList<>();
        List<Event> duplicateEvents = new ArrayList<>();

        for (String part : eventParts) {
            assert part != null && !part.trim().isEmpty() : "Each event entry should not be null or empty";

            String[] details = EventValidator.validate(part);
            assert details.length == 3 : "Validated event details should have exactly three elements (description, start time, and end time)";

            Event event = new Event(details[0].trim(), details[1].trim(), details[2].trim());

            if (DuplicateEventChecker.isDuplicate(tasks, event)) {
                duplicateEvents.add(event);
                continue; // Skip adding duplicate events
            }

            tasks.add(event);
            addedEvents.add(event);
        }

        storage.save(tasks.getTasks());

        return AddEventResponse.generateResponse(addedEvents, duplicateEvents, tasks);
    }
}
