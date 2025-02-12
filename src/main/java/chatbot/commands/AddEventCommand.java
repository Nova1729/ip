package chatbot.commands;

import chatbot.Storage;
import chatbot.Ui;
import chatbot.exceptions.EventException;
import chatbot.tasks.Event;
import chatbot.tasks.TaskList;
import chatbot.check.CheckEvent;

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
     * @param ui      The {@link Ui} instance (not used in this version).
     * @param storage The {@link Storage} instance to handle saving/loading tasks from storage.
     * @return A string response confirming the event tasks have been added.
     * @throws EventException If any input does not contain the required "/from" and "/to" clauses.
     * @throws Exception      If an error occurs during saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui instance cannot be null";
        assert storage != null : "Storage instance cannot be null";

        String[] eventParts = input.split(";");
        assert eventParts.length > 0 : "Event input should not result in an empty array";

        List<Event> events = new ArrayList<>();
        for (String part : eventParts) {
            assert part != null && !part.trim().isEmpty() : "Each event entry should not be null or empty";

            String[] details = CheckEvent.validate(part);
            assert details.length == 3 : "Validated event details should have exactly three elements (description, start time, and end time)";

            Event event = new Event(details[0].trim(), details[1].trim(), details[2].trim());

            tasks.add(event);
            events.add(event);
        }

        storage.save(tasks.getTasks());
        assert tasks.size() > 0 : "TaskList should have at least one task after adding events";

        StringBuilder response = new StringBuilder("Got it. I've added these tasks:\n");
        for (Event event : events) {
            response.append("  ").append(event).append("\n");
        }
        response.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
        return response.toString().trim();
    }
}
