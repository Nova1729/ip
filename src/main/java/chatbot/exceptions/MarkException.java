package chatbot.exceptions;

/**
 * Exception thrown when an invalid mark command is encountered.
 */
public class MarkException extends Exception {
    public MarkException(String message) {
        super(message);
    }
}
