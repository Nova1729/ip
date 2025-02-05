package chatbot.commands;

import chatbot.Storage;
import chatbot.Ui;
import chatbot.exceptions.EventException;
import chatbot.tasks.Event;
import chatbot.tasks.TaskList;

/**
 * Represents a command to add an event task to the task list.
 */
public class AddEventCommand extends Command {

    /** The input string containing the event description and time details. */
    private final String input;

    /**
     * Constructs an {@code AddEventCommand} with the specified input.
     *
     * @param input The input string containing the event description and time in the format:
     *              "description /from start_time /to end_time".
     */
    public AddEventCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to add an event task to the task list.
     * Validates the input, creates the event task, and saves it to the storage.
     *
     * @param tasks   The {@link TaskList} containing the current list of tasks.
     * @param ui      The {@link Ui} instance to handle user interactions.
     * @param storage The {@link Storage} instance to handle saving/loading tasks from storage.
     * @throws EventException If the input does not contain the required "/from" and "/to" clauses.
     * @throws Exception If an error occurs during saving tasks to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new EventException("The description of an event must include /from and /to clauses.");
        }
        String[] details = input.split(" /from | /to ", 3);
        Event event = new Event(details[0], details[1], details[2]);
        tasks.add(event);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("  " + event);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getTasks());
    }
}


