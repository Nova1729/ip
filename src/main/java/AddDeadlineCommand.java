public class AddDeadlineCommand extends Command {
    private final String input;

    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (!input.contains(" /by ")) {
            throw new DeadlineException("The description of a deadline must include a /by clause.");
        }
        String[] details = input.split(" /by ", 2);
        Deadline deadline = new Deadline(details[0], details[1]);
        tasks.add(deadline);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("  " + deadline);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getTasks());
    }
}

