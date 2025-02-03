public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (description.trim().isEmpty()) {
            throw new TodoException("The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(description);
        tasks.add(todo);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("  " + todo);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getTasks());
    }
}

