package chatbot;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class UiTest {

    @Test
    public void testShowWelcome() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Ui ui = new Ui();
        ui.showWelcome();

        String expectedOutput = "____________________________________________________________\n" +
                " Hello! I'm Friday\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        assertEquals(expectedOutput, outputStream.toString());

        // Reset System.out to its original state
        System.setOut(System.out);
    }

    @Test
    public void testReadCommand() {
        String input = "todo Read a book\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Ui ui = new Ui();
        String command = ui.readCommand();

        assertEquals("todo Read a book", command);

        System.setIn(System.in);
    }

    @Test
    public void testShowLine() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Ui ui = new Ui();
        ui.showLine();

        String expectedOutput = "____________________________________________________________\n";

        assertEquals(expectedOutput, outputStream.toString());

        System.setOut(System.out);
    }

    @Test
    public void testShowMessage() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Ui ui = new Ui();
        ui.showMessage("This is a test message.");

        String expectedOutput = "This is a test message.\n";

        assertEquals(expectedOutput, outputStream.toString());

        System.setOut(System.out);
    }

    @Test
    public void testShowError() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Ui ui = new Ui();
        ui.showError("This is a test error.");

        String expectedOutput = "OOPS!!! This is a test error.\n";

        assertEquals(expectedOutput, outputStream.toString());

        System.setOut(System.out);
    }
}

