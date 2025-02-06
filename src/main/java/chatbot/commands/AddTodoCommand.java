package chatbot.commands;

import chatbot.Storage;
import chatbot.Ui;
import chatbot.exceptions.TodoException;
import chatbot.tasks.TaskList;
import chatbot.tasks.Todo;

/**
 * Represents a command to add a todo task to the task list.
 */
public class AddTodoCommand extends Command {

    /** The description of the todo task. */
    private final String description;

    /**
     * Constructs an {@code AddTodoCommand} with the specified description.
     *
     * @param description The description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a todo task to the task list.
     * Validates the description, creates the todo task, and saves it to the storage.
     *
     * @param tasks   The {@link TaskList} containing the current list of tasks.
     * @param ui      The {@link Ui} instance to handle user interactions.
     * @param storage The {@link Storage} instance to handle saving/loading tasks from storage.
     * @return
     * @throws TodoException If the description of the todo is empty.
     * @throws Exception     If an error occurs during saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Todo todo = new Todo(description);
        tasks.add(todo);
        storage.save(tasks.getTasks());
        return "Got it. I've added this task:\n  " + todo + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}



