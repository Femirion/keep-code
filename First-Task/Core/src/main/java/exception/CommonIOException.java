package exception;

public class CommonIOException extends CommonException{
    public CommonIOException(String message) {
        super(message);
    }

    public CommonIOException(String message, Throwable cause) {
        super(message, cause);
    }
}
