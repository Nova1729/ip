package chatbot;

import chatbot.tasks.*;
import chatbot.*;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * The Storage class handles reading and writing tasks to and from a storage file.
 * It abstracts file operations, allowing the chatbot to persist tasks across sessions.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the storage file into an {@link ArrayList}.
     * If the file does not exist, it returns an empty task list.
     *
     * @return An {@link ArrayList} of tasks loaded from the file.
     * @throws IOException If an I/O error occurs during file reading.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            return tasks;
        }
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Parser.parseTask(line));
            }
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     * If the file's parent directories do not exist, they are created.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an I/O error occurs during file writing.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        Files.createDirectories(filePath.getParent());
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        }
    }
}

