package chatbot.check;

import chatbot.exceptions.DeleteException;
import chatbot.tasks.TaskList;

/**
 * Utility class to validate delete command input.
 */
public class CheckDelete {

    /**
     * Validates if the given input is a numeric task index and within the valid range.
     *
     * @param input The raw input string representing a task index.
     * @param tasks The task list to check the index against.
     * @return The valid integer index.
     * @throws DeleteException If the input is not numeric or the index is out of range.
     */
    public static int validate(String input, TaskList tasks) throws DeleteException {
        int index;

        // Check if input is numeric
        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DeleteException("OOPS!!! The delete command requires a valid numeric task number.");
        }

        // Check if the index is within range
        if (index < 1 || index > tasks.size()) {
            throw new DeleteException("OOPS!!! The task number provided is out of range.");
        }

        return index;
    }
}
