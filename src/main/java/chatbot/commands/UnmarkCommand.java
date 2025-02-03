package chatbot.commands;

import chatbot.tasks.*;
import chatbot.Ui;
import chatbot.Storage;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task number.");
        }
        Task task = tasks.get(index - 1);
        task.markAsNotDone();
        ui.showMessage("OK, I've marked this task as not done yet:");
        ui.showMessage("  " + task);
        storage.save(tasks.getTasks());
    }
}


