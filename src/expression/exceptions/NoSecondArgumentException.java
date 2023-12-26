package expression.exceptions;

public class NoSecondArgumentException extends ParserException{
    public NoSecondArgumentException() {
        super();
    }

    public NoSecondArgumentException(String message) {
        super(message);
    }

    public NoSecondArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
