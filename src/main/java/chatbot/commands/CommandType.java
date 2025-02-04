package chatbot.commands;

/**
 * Represents the different types of commands that the chatbot can process.
 */
public enum CommandType {
    /** Command to exit the chatbot. */
    BYE,

    /** Command to list all tasks. */
    LIST,

    /** Command to mark a task as completed. */
    MARK,

    /** Command to unmark a task as incomplete. */
    UNMARK,

    /** Command to add a new todo task. */
    TODO,

    /** Command to add a new deadline task. */
    DEADLINE,

    /** Command to add a new event task. */
    EVENT,

    /** Command to delete a task from the task list. */
    DELETE,

    /** Represents an unknown or invalid command. */
    UNKNOWN;

    /**
     * Converts a string input into a corresponding {@link CommandType} enum value.
     * If the input does not match any valid command, it returns {@code UNKNOWN}.
     *
     * @param input The string representation of the command.
     * @return The corresponding {@link CommandType}, or {@code UNKNOWN} if the input is invalid.
     */
    public static CommandType toCommandType(String input) {
        try {
            return CommandType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}


