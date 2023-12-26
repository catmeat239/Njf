package expression.exceptions;

public class NoFirstArgumentException extends ParserException {
    public NoFirstArgumentException() {
        super();
    }

    public NoFirstArgumentException(String message) {
        super(message);
    }

    public NoFirstArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
