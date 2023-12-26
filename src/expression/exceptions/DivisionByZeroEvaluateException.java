package expression.exceptions;

public class DivisionByZeroEvaluateException extends EvaluationException {
    public DivisionByZeroEvaluateException() {
        super();
    }

    public DivisionByZeroEvaluateException(String message) {
        super(message);
    }

    public DivisionByZeroEvaluateException(String message, Throwable cause) {
        super(message, cause);
    }
}
