package chatbot.commands;

import chatbot.Storage;
import chatbot.Ui;
import chatbot.exceptions.DeadlineException;
import chatbot.tasks.Deadline;
import chatbot.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to add multiple deadline tasks to the task list.
 */
public class AddDeadlineCommand extends Command {

    private final String input;

    /**
     * Constructs an {@code AddDeadlineCommand} with the specified input.
     *
     * @param input The input string containing the task descriptions and deadlines
     *              in the format: "description /by date; description /by date".
     */
    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        // Split the input into multiple deadlines using ";" as a separator
        String[] deadlineParts = input.split(";");

        List<Deadline> deadlines = new ArrayList<>();
        for (String part : deadlineParts) {
            // Validate the presence of the /by clause
            if (!part.contains(" /by ")) {
                throw new DeadlineException("The description of a deadline must include a /by clause.");
            }

            // Split the description and deadline date
            String[] details = part.trim().split(" /by ", 2);
            if (details.length < 2 || details[0].isEmpty() || details[1].isEmpty()) {
                throw new DeadlineException("Each deadline must have a valid description and /by clause.");
            }

            // Create the Deadline task and add it to the list
            Deadline deadline = new Deadline(details[0].trim(), details[1].trim());
            tasks.add(deadline);
            deadlines.add(deadline);
        }

        // Save the updated task list
        storage.save(tasks.getTasks());

        // Generate the response for all added deadlines
        StringBuilder response = new StringBuilder("Got it. I've added these tasks:\n");
        for (Deadline deadline : deadlines) {
            response.append("  ").append(deadline).append("\n");
        }
        response.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
        return response.toString().trim();
    }
}




