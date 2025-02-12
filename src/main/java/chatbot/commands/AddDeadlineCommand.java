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
        assert input != null && !input.trim().isEmpty() : "Input for AddDeadlineCommand cannot be null or empty";

        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui instance cannot be null";
        assert storage != null : "Storage instance cannot be null";

        String[] deadlineParts = input.split(";");
        assert deadlineParts.length > 0 : "Deadline input should not result in an empty array";

        List<Deadline> deadlines = new ArrayList<>();
        for (String part : deadlineParts) {
            assert part != null && !part.trim().isEmpty() : "Each deadline entry should not be null or empty";

            String[] details = CheckDeadline.validate(part);
            assert details.length == 2 : "Validated deadline details should have exactly two elements (description and due date)";

            Deadline deadline = new Deadline(details[0].trim(), details[1].trim());

            tasks.add(deadline);
            deadlines.add(deadline);
        }

        storage.save(tasks.getTasks());
        assert tasks.size() > 0 : "TaskList should have at least one task after adding deadlines";

        // Generate the response for all added deadlines
        StringBuilder response = new StringBuilder("Got it. I've added these tasks:\n");
        for (Deadline deadline : deadlines) {
            response.append("  ").append(deadline).append("\n");
        }
        response.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
        return response.toString().trim();
    }
}





