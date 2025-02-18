package chatbot.commands;

import chatbot.Storage;
import chatbot.exceptions.TodoException;
import chatbot.responses.AddTodoResponse;
import chatbot.tasks.TaskList;
import chatbot.tasks.Todo;
import chatbot.checkduplicates.DuplicateTodoChecker;

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
        assert input != null && !input.trim().isEmpty() : "Input for AddTodoCommand cannot be null or empty";
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
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage instance cannot be null";

        String[] todoParts = input.split(";");
        assert todoParts.length > 0 : "Todo input should not result in an empty array";

        List<Todo> todos = new ArrayList<>();
        List<Todo> duplicateTodos = new ArrayList<>();

        for (String part : todoParts) {
            assert part != null && !part.trim().isEmpty() : "Each todo entry should not be null or empty";

            String description = part.trim();
            Todo todo = new Todo(description);

            if (DuplicateTodoChecker.isDuplicate(tasks, todo)) {
                duplicateTodos.add(todo);
            } else {
                tasks.add(todo);
                todos.add(todo);
            }
        }

        storage.save(tasks.getTasks());
        return AddTodoResponse.generate(todos, duplicateTodos, tasks);
    }
}


