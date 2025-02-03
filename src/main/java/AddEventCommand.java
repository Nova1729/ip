public class AddEventCommand extends Command {
    private final String input;

    public AddEventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new EventException("The description of an event must include /from and /to clauses.");
        }
        String[] details = input.split(" /from | /to ", 3);
        Event event = new Event(details[0], details[1], details[2]);
        tasks.add(event);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("  " + event);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getTasks());
    }
}

