package chatbot.commands;

import chatbot.Storage;
import chatbot.checkduplicates.DuplicateDeadlineChecker;
import chatbot.responses.AddDeadlineResponse;
import chatbot.tasks.Deadline;
import chatbot.tasks.TaskList;
import chatbot.validation.DeadlineValidator;

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
    public String execute(TaskList tasks, Storage storage) throws Exception {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage instance cannot be null";

        String[] deadlineParts = input.split(";");
        assert deadlineParts.length > 0 : "Deadline input should not result in an empty array";

        List<Deadline> addedDeadlines = new ArrayList<>();
        List<Deadline> duplicateDeadlines = new ArrayList<>();

        // originally my code for this execute method was too long, so I asked chatGPT to
        // help me break the method into different classes (AddDeadlineResponse
        // and DuplicateDeadlineChecker) that are placed in different packages
        // (responses and checkduplicates). The same was done for AddEventCommand and
        // AddTodoCommand.
        // Also, the JavaDoc for the different commands are quite repetitive, so I wrote for
        // class and asked chatGPT to generate the rest for me.
        for (String part : deadlineParts) {
            assert part != null && !part.trim().isEmpty() : "Each deadline entry should not be null or empty";

            String[] details = DeadlineValidator.validate(part);
            assert details.length == 2 : "Validated deadline details should have exactly two elements (description and due date)";

            Deadline deadline = new Deadline(details[0].trim(), details[1].trim());

            // Check for duplicates before adding
            if (DuplicateDeadlineChecker.isDuplicate(tasks, deadline)) {
                duplicateDeadlines.add(deadline);
            } else {
                tasks.add(deadline);
                addedDeadlines.add(deadline);
            }
        }

        storage.save(tasks.getTasks());
        return AddDeadlineResponse.generate(addedDeadlines, duplicateDeadlines, tasks.size());
    }
}



