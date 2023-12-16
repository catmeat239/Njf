package expression.exceptions;

import base.Selector;
import expression.TripleExpression;

import static expression.parser.Operations.*;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public final class ExceptionsTest {
    private static final ExpressionParser PARSER = new ExpressionParser();
    private static final Operation TRIPLE = kind(TripleExpression.KIND, (expr, variables) -> PARSER.parse(expr));

    public static final Selector SELECTOR = Selector.composite(ExceptionsTest.class, ExceptionsTester::new, "easy", "hard")
            .variant("Base", TRIPLE, ADD, SUBTRACT, MULTIPLY, DIVIDE, NEGATE)
            .selector();

    private ExceptionsTest() {
    }

    public static void main(final String... args) {
        SELECTOR.main(args);
    }

    public static class DivisionByZeroEvaluateException extends EvaluationException {
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
}
