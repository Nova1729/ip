package chatbot.commands;

import chatbot.Storage;
import chatbot.exceptions.TodoException;
import chatbot.responses.AddTodoResponse;
import chatbot.tasks.TaskList;
import chatbot.tasks.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to add multiple todo tasks to the task list.
 */
public class AddTodoCommand extends Command {

    /** The input string containing multiple todo descriptions. */
    private final String input;

    /**
     * Constructs an {@code AddTodoCommand} with the specified input.
     *
     * @param input The input string containing multiple todo descriptions separated by semicolons.
     */
    public AddTodoCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to add multiple todo tasks to the task list.
     * Splits the input by semicolons and validates each todo description.
     *
     * @param tasks   The {@link TaskList} containing the current list of tasks.
     * @param storage The {@link Storage} instance to handle saving/loading tasks from storage.
     * @return A response string confirming the added todo tasks.
     * @throws TodoException If any of the todos have empty descriptions.
     * @throws Exception     If an error occurs during saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws Exception {
        // Split the input into multiple todos using ";" as a separator
        String[] todoParts = input.split(";");

        List<Todo> todos = new ArrayList<>();
        for (String part : todoParts) {
            String description = part.trim();

            // Create the Todo task and add it to the list
            Todo todo = new Todo(description);
            tasks.add(todo);
            todos.add(todo);
        }

        // Save the updated task list
        storage.save(tasks.getTasks());

        return AddTodoResponse.generate(todos, tasks);
    }
}


