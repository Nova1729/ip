package chatbot.commands;

import chatbot.Ui;
import chatbot.Storage;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;

/**
 * Represents a command to mark a specific task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task number.");
        }
        Task task = tasks.get(index - 1);
        task.markAsDone();
        storage.save(tasks.getTasks());
        return "Nice! I've marked this task as done:\n  " + task;
    }
}




