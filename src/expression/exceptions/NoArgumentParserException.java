package expression.exceptions;

public class NoArgumentParserException extends ParserException {

    public NoArgumentParserException() {
        super();
    }

    public NoArgumentParserException(String message) {
        super(message);
    }

    public NoArgumentParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
