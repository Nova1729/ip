package chatbot.commands;

import chatbot.Storage;
import chatbot.Ui;
import chatbot.exceptions.DeadlineException;
import chatbot.tasks.Deadline;
import chatbot.tasks.TaskList;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {

    /** The input string containing the description and deadline details. */
    private final String input;

    /**
     * Constructs an {@code AddDeadlineCommand} with the specified input.
     *
     * @param input The input string containing the task
     *     description and the deadline in the format: "description /by date".
     */
    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to add a deadline task to the task list.
     * Validates the input, creates the deadline task, and saves it to the storage.
     *
     * @param tasks   The {@link TaskList} containing the current list of tasks.
     * @param ui      The {@link Ui} instance to handle user interactions.
     * @param storage The {@link Storage} instance to handle saving/loading tasks from storage.
     * @return
     * @throws DeadlineException If the input does not contain the required "/by" clause.
     * @throws Exception         If an error occurs during saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (!input.contains(" /by ")) {
            throw new DeadlineException("The description of a deadline must include a /by clause.");
        }

        String[] details = input.split(" /by ", 2);
        Deadline deadline = new Deadline(details[0], details[1]);
        tasks.add(deadline);

        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("  " + deadline);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getTasks());
        return null;
    }
}



