package expression.exceptions;

public class NoSeparatorParserException extends ParserException{
    public NoSeparatorParserException() {
        super();
    }

    public NoSeparatorParserException(String message) {
        super(message);
    }

    public NoSeparatorParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
