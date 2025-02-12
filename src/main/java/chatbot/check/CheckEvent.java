package chatbot.check;

import chatbot.exceptions.EventException;

/**
 * A utility class for validating event input strings.
 */
public class CheckEvent {

    /**
     * Validates the input string for an event task.
     *
     * @param part The input string to validate.
     * @return A string array containing the description, start time, and end time.
     * @throws EventException If the input does not meet the required format.
     */
    public static String[] validate(String part) throws EventException {
        assert part != null : "Input event description cannot be null";

        // Check if the input contains the /from and /to clauses
        if (!part.contains(" /from ") || !part.contains(" /to ")) {
            throw new EventException("Each event description must include /from and /to clauses." +
                    " If you want to add multiple events all at once, ensure that each event is separated by '; '");
        }

        // Split the description, start time, and end time
        String[] details = part.trim().split(" /from | /to ", 3);

        if (details.length < 3 || details[0].isEmpty() || details[1].isEmpty() || details[2].isEmpty()) {
            throw new EventException("Each event must have a valid description, start time, and end time." +
                    " If you want to add multiple events all at once, ensure that each event is separated by '; '");
        }

        return details;
    }
}

