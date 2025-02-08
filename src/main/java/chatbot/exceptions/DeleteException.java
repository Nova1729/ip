package chatbot.exceptions;

/**
 * Exception thrown when an invalid delete command is encountered.
 */
public class DeleteException extends Exception {
    public DeleteException(String message) {
        super(message);
    }
}

