package chatbot.commands;

import chatbot.tasks.*;
import chatbot.Ui;
import chatbot.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    public boolean isExit() {
        return false;
    }
}


