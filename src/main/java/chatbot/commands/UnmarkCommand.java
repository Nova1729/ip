package chatbot.commands;

import chatbot.Ui;
import chatbot.Storage;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;

/**
 * Represents a command to unmark a specific task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task number.");
        }
        Task task = tasks.get(index - 1);
        task.markAsNotDone();
        storage.save(tasks.getTasks());
        return "OK, I've marked this task as not done yet:\n  " + task;
    }
}




