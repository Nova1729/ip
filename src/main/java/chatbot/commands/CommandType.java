package chatbot.commands;

import chatbot.tasks.*;

public enum CommandType {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN;

    public static CommandType toCommandType(String input) {
        try {
            return CommandType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}

