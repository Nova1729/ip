package chatbot.check;

import chatbot.exceptions.DeadlineException;

/**
 * A utility class for validating deadline input strings.
 */
public class CheckDeadline {

    /**
     * Validates the input string for a deadline task.
     *
     * @param part The input string to validate.
     * @throws DeadlineException If the input does not meet the required format.
     */
    public static String[] validate(String part) throws DeadlineException {
        assert part != null : "Input deadline task cannot be null";

        // Check if the input contains the /by clause
        if (!part.contains(" /by ")) {
            throw new DeadlineException("The description of a deadline must include a /by clause." +
                    " If you want to add multiple deadlines all at once, ensure that each deadline is separated by '; '");
        }

        // Split the description and deadline date
        String[] details = part.trim().split(" /by ", 2);
        if (details.length < 2 || details[0].isEmpty() || details[1].isEmpty()) {
            throw new DeadlineException("Each deadline must have a valid description after the /by clause." +
                    " If you want to add multiple deadlines all at once, ensure that each deadline is separated by '; '");
        }

        return details;
    }
}

