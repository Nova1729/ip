package chatbot.commands;


import chatbot.Storage;
import chatbot.Ui;
import chatbot.tasks.Deadline;
import chatbot.tasks.TaskList;
import chatbot.check.CheckDeadline;

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
            // Use the CheckDeadline utility to validate and parse the input
            String[] details = CheckDeadline.validate(part);

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





