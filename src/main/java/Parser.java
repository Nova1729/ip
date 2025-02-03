public class Parser {
    public static Command parse(String fullCommand) throws UnknownCommandException {
        String[] inputParts = fullCommand.split(" ", 2);
        CommandType commandType = CommandType.toCommandType(inputParts[0]);
        switch (commandType) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkCommand(Integer.parseInt(inputParts[1]));
            case UNMARK:
                return new UnmarkCommand(Integer.parseInt(inputParts[1]));
            case TODO:
                return new AddTodoCommand(inputParts[1]);
            case DEADLINE:
                return new AddDeadlineCommand(inputParts[1]);
            case EVENT:
                return new AddEventCommand(inputParts[1]);
            case DELETE:
                return new DeleteCommand(Integer.parseInt(inputParts[1]));
            default:
                throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static Task parseTask(String line) {
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
    }
}

