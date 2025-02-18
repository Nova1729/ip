package chatbot.validation;

import chatbot.exceptions.MarkException;
import chatbot.tasks.TaskList;

/**
 * Utility class to validate the mark command input.
 */
public class MarkValidator {

    /**
     * Validates if the given input is a numeric task index and within the valid range.
     *
     * @param input The raw input string representing a task index.
     * @param tasks The task list to validation the index against.
     * @return The valid integer index.
     * @throws MarkException If the input is not numeric or the index is out of range.
     */
    public static int validate(String input, TaskList tasks) throws MarkException {
        assert input != null : "Input task number cannot be null";

        int index;

        // Check if input is numeric
        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new MarkException("OOPS!!! The mark command requires a valid numeric task number.");
        }

        // Check if the index is within range
        if (index < 1 || index > tasks.size()) {
            throw new MarkException("OOPS!!! The task number provided is out of range.");
        }

        return index;
    }
}

