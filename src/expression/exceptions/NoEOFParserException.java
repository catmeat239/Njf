package expression.exceptions;

public class NoEOFParserException extends ParserException{
    public NoEOFParserException() {
        super();
    }

    public NoEOFParserException(String message) {
        super(message);
    }

    public NoEOFParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
