package expression.exceptions;

public class OverflowEvaluateException extends EvaluationException {
    public OverflowEvaluateException() {
        super();
    }

    public OverflowEvaluateException(String message) {
        super(message);
    }

    public OverflowEvaluateException(String message, Throwable cause) {
        super(message, cause);
    }
}
