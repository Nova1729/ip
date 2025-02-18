package chatbot.validation;

import chatbot.exceptions.DeleteException;
import chatbot.tasks.TaskList;

/**
 * Utility class to validate delete command input.
 */
public class DeleteValidator {

    /**
     * Validates if the given input is a numeric task index and within the valid range.
     *
     * @param input The raw input string representing a task index.
     * @param tasks The task list to validation the index against.
     * @return The valid integer index.
     * @throws DeleteException If the input is not numeric or the index is out of range.
     */
    public static int validate(String input, TaskList tasks) throws DeleteException {
        assert input != null : "Input should not be null";
        assert tasks != null : "Task list should not be null";

        int index;

        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DeleteException("OOPS!!! The delete command requires a valid numeric task number.");
        }

        if (index < 1 || index > tasks.size()) {
            throw new DeleteException("OOPS!!! The task number provided is out of range.");
        }

        return index;
    }
}
