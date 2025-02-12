package chatbot;

import chatbot.commands.Command;
import chatbot.tasks.TaskList;

/**
 * Represents the main chatbot application, Friday.
 * Handles the initialization of core components (UI, storage, task list)
 * and the main execution loop.
 */
public class Friday {
    private final Storage storage;
    private TaskList tasks;
    private boolean isExit = false;

    /**
     * Constructs a Friday chatbot instance.
     *
     * @param filePath The file path where tasks are saved and loaded from.
     */
    public Friday(String filePath) {
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * The main entry point of the chatbot application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

    }

    public boolean isExit() {
        return isExit;
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(tasks, storage);

            if (command.isExit()) {
                isExit = true; // Set exit flag if the command is an exit command
            }

            return response;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}









