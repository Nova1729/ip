package chatbot.commands;

import chatbot.Storage;
import chatbot.Ui;
import chatbot.exceptions.EventException;
import chatbot.tasks.Event;
import chatbot.tasks.TaskList;

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
        // Split the input into multiple events using ";" as a separator
        String[] eventParts = input.split(";");

        List<Event> events = new ArrayList<>();
        for (String part : eventParts) {
            // Check if input contains /from and /to
            if (!part.contains(" /from ") || !part.contains(" /to ")) {
                throw new EventException("Each event description must include /from and /to clauses." +
                        " If you want to add multiple events all at once, ensure that each event is separated by '; '");
            }

            // Extract description, start, and end times
            String[] details = part.trim().split(" /from | /to ", 3);
            if (details.length < 3 || details[0].isEmpty() || details[1].isEmpty() || details[2].isEmpty()) {
                throw new EventException("Each event must have a valid description, start time, and end time." +
                        " If you want to add multiple events all at once, ensure that each event is separated by '; '");
            }

            // Keep the input times as they are without reformatting
            String startTime = details[1].trim();
            String endTime = details[2].trim();

            // Create the Event task and add it to the list
            Event event = new Event(details[0].trim(), startTime, endTime);
            tasks.add(event);
            events.add(event);
        }

        // Save tasks
        storage.save(tasks.getTasks());

        // Generate response
        StringBuilder response = new StringBuilder("Got it. I've added these tasks:\n");
        for (Event event : events) {
            response.append("  ").append(event).append("\n");
        }
        response.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
        return response.toString().trim();
    }
}




