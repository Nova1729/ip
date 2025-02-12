package chatbot;

import chatbot.commands.AddDeadlineCommand;
import chatbot.commands.AddEventCommand;
import chatbot.commands.AddTodoCommand;
import chatbot.commands.Command;
import chatbot.commands.CommandType;
import chatbot.commands.DeleteCommand;
import chatbot.commands.ExitCommand;
import chatbot.commands.FindCommand;
import chatbot.commands.ListCommand;
import chatbot.commands.MarkCommand;
import chatbot.commands.UnmarkCommand;
import chatbot.exceptions.DeadlineException;
import chatbot.exceptions.EventException;
import chatbot.exceptions.TodoException;
import chatbot.exceptions.UnknownCommandException;
import chatbot.tasks.Deadline;
import chatbot.tasks.Event;
import chatbot.tasks.Task;
import chatbot.tasks.Todo;

/**
 * The Parser class is responsible for interpreting user input and converting it into
 * the appropriate {@link Command} or {@link Task}.
 */
public class Parser {

    /**
     * Parses the full user command and converts it into a {@link Command} object.
     *
     * @param fullCommand The raw input string provided by the user.
     * @return A {@link Command} object corresponding to the user input.
     * @throws UnknownCommandException If the command is unrecognized.
     * @throws TodoException If the TODO command is missing a description.
     * @throws DeadlineException If the DEADLINE command is missing the `/by` clause.
     * @throws EventException If the EVENT command is missing the `/from` or `/to` clauses.
     */
    public static Command parse(String fullCommand)
            throws UnknownCommandException, TodoException, DeadlineException, EventException {

        String[] inputParts = fullCommand.split(" ", 2);
        CommandType commandType = CommandType.toCommandType(inputParts[0]);

        switch (commandType) {
        case BYE:
            return new ExitCommand();

        case LIST:
            return new ListCommand();

        case MARK:
            if (inputParts.length < 2) {
                throw new IllegalArgumentException("OOPS!!! The mark command requires a task number.");
            }
            return new MarkCommand(inputParts[1]);

        case UNMARK:
            if (inputParts.length < 2) {
                throw new IllegalArgumentException("OOPS!!! The unmark command requires a task number.");
            }
            return new UnmarkCommand(inputParts[1]);

        case TODO:
            if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                throw new TodoException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddTodoCommand(inputParts[1]);

        case DEADLINE:
            if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                throw new DeadlineException("OOPS!!! The description of a deadline cannot be empty.");
            }
            return new AddDeadlineCommand(inputParts[1]);

        case EVENT:
            if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                throw new EventException("OOPS!!! The description of an event cannot be empty.");
            }
            return new AddEventCommand(inputParts[1]);

        case DELETE:
            if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                throw new IllegalArgumentException("OOPS!!! The delete command requires a task number.");
            }
            return new DeleteCommand(inputParts[1]); // Pass the input as a string


        case FIND:
            if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                throw new IllegalArgumentException("OOPS!!! The find command requires a keyword.");
            }
            return new FindCommand(inputParts[1]);

        default:
            throw new UnknownCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses a single line from the storage file and converts it into a {@link Task}.
     *
     * @param line A line from the storage file in the format used for saving tasks.
     * @return A {@link Task} object reconstructed from the file.
     * @throws IllegalArgumentException If the line format is corrupted or invalid.
     */
    public static Task parseTask(String line) throws IllegalArgumentException {
        try {
            String[] parts = line.split(" \\| ");

            switch (parts[0]) {
            case "T":
                return new Todo(parts[2], parts[1].equals("1"));

            case "D":
                return new Deadline(parts[2], parts[3], parts[1].equals("1"));

            case "E":
                return new Event(parts[2], parts[3], parts[4], parts[1].equals("1"));

            default:
                throw new IllegalArgumentException("Unknown task type: " + parts[0]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Corrupted or invalid task format: " + line, e);
        }
    }
}




