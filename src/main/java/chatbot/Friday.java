package chatbot;

import chatbot.commands.Command;
import chatbot.tasks.TaskList;

/**
 * Represents the main chatbot application, Friday.
 * Handles the initialization of core components (UI, storage, task list)
 * and the main execution loop.
 */
public class Friday {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Friday chatbot instance.
     *
     * @param filePath The file path where tasks are saved and loaded from.
     */
    public Friday(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot, displaying the welcome message
     * and handling user commands in a loop until the exit command is given.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main entry point of the chatbot application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Friday("CS2103T/CS2103T_chatBot_Tasks/Friday.txt").run();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(tasks, ui, storage);
            return response;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}









