package chatbot.exceptions;

/**
 * Exception thrown when an invalid unmark command is encountered.
 */
public class UnmarkException extends Exception {
    public UnmarkException(String message) {
        super(message);
    }
}
