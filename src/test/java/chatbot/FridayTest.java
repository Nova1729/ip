package chatbot;

import chatbot.commands.Command;
import chatbot.commands.ExitCommand;
import chatbot.exceptions.DeadlineException;
import chatbot.exceptions.EventException;
import chatbot.exceptions.TodoException;
import chatbot.exceptions.UnknownCommandException;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FridayTest {

    @Test
    public void testAddTodoCommand() throws IOException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test/Friday.txt");

        Todo todo = new Todo("Read a book");
        tasks.add(todo);
        assertEquals(1, tasks.size());
        assertEquals("[T][ ] Read a book", tasks.get(0).toString());
    }

    @Test
    public void testParserParseCommand() throws DeadlineException, EventException, TodoException {
        try {
            Command command = Parser.parse("todo read a book");
            assertNotNull(command);
        } catch (UnknownCommandException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testStorageSaveAndLoad() throws IOException {
        Storage storage = new Storage("test/Friday.txt");
        ArrayList<Task> testTasks = new ArrayList<>();
        testTasks.add(new Todo("Read a book", false));

        storage.save(testTasks);

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertEquals("[T][ ] Read a book", loadedTasks.get(0).toString());
    }

    @Test
    public void testExitCommand() {
        Ui ui = new Ui();
        Storage storage = new Storage("test/Friday.txt");
        TaskList tasks = new TaskList();

        Command exitCommand = new ExitCommand();
        try {
            exitCommand.execute(tasks, ui, storage);
            assertTrue(exitCommand.isExit());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}

