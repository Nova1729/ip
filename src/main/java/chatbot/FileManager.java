package chatbot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import chatbot.tasks.Deadline;
import chatbot.tasks.Event;
import chatbot.tasks.Task;
import chatbot.tasks.Todo;

/**
 * The FileManager class is responsible for handling file I/O operations
 * for saving and loading tasks to/from a persistent storage file.
 */
public class FileManager {
    private static final Path FILE_PATH = Paths.get("CS2103T", "CS2103T_chatBot_Tasks", "Friday.txt");

    /**
     * Saves a list of tasks to a file.
     *
     * @param tasks The list of tasks to save.
     */
    public static void saveTasks(List<Task> tasks) {
        try {
            Files.createDirectories(FILE_PATH.getParent()); // Ensure directories exist
            try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH)) {
                for (Task task : tasks) {
                    writer.write(taskToString(task));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from a file.
     *
     * @return An ArrayList of tasks loaded from the file. If the file does not exist, an empty list is returned.
     * @throws IOException If an error occurs during file reading.
     */
    public static ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(FILE_PATH)) {
            return tasks;
        }
        try (BufferedReader reader = Files.newBufferedReader(FILE_PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(parseTask(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Converts a Task object to its string representation for saving to a file.
     *
     * @param task The task to convert.
     * @return The string representation of the task.
     */
    private static String taskToString(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + event.getFrom() + " | "
                    + event.getTo();
        }
        return "";
    }

    /**
     * Parses a string representation of a task and converts it back into a Task object.
     *
     * @param line The string representation of the task.
     * @return The Task object represented by the string.
     * @throws IllegalArgumentException If the task type in the string is unknown or invalid.
     */
    private static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
        case "T": {
            Todo todo = new Todo(parts[2]);
            if (parts[1].equals("1")) {
                todo.markAsDone();
            }
            return todo;
        }
        case "D": {
            Deadline deadline = new Deadline(parts[2], parts[3]);
            if (parts[1].equals("1")) {
                deadline.markAsDone();
            }
            return deadline;
        }
        case "E": {
            Event event = new Event(parts[2], parts[3], parts[4]);
            if (parts[1].equals("1")) {
                event.markAsDone();
            }
            return event;
        }
        default:
            throw new IllegalArgumentException("Unknown task type in file: " + parts[0]);
        }
    }
}



