package exception;

public class InteractionException extends CommonException{
    public InteractionException(String message) {
        super(message);
    }

    public InteractionException(String message, Throwable cause) {
        super(message, cause);
    }
}
