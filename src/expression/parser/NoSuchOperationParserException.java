package expression.parser;

import expression.exceptions.ParserException;

public class NoSuchOperationParserException extends ParserException {

    public NoSuchOperationParserException() {
        super();
    }

    public NoSuchOperationParserException(String message) {
        super(message);
    }

    public NoSuchOperationParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
