package chatbot;

import chatbot.commands.*;
import chatbot.exceptions.*;
import chatbot.tasks.*;

public class Parser {
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
            return new MarkCommand(Integer.parseInt(inputParts[1]));

        case UNMARK:
            if (inputParts.length < 2) {
                throw new IllegalArgumentException("OOPS!!! The unmark command requires a task number.");
            }
            return new UnmarkCommand(Integer.parseInt(inputParts[1]));

        case TODO:
            if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                throw new TodoException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddTodoCommand(inputParts[1]);

        case DEADLINE:
            if (inputParts.length < 2 || !inputParts[1].contains(" /by ")) {
                throw new DeadlineException("OOPS!!! The description of a deadline must include a /by clause.");
            }
            return new AddDeadlineCommand(inputParts[1]);

        case EVENT:
            if (inputParts.length < 2 || !inputParts[1].contains(" /from ") || !inputParts[1].contains(" /to ")) {
                throw new EventException("OOPS!!! The description of an event must include /from and /to clauses.");
            }
            return new AddEventCommand(inputParts[1]);

        case DELETE:
            if (inputParts.length < 2) {
                throw new IllegalArgumentException("OOPS!!! The delete command requires a task number.");
            }
            return new DeleteCommand(Integer.parseInt(inputParts[1]));

        default:
            throw new UnknownCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

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




