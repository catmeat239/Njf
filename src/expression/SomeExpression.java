package expression;

public interface SomeExpression extends Expression, TripleExpression, BigDecimalExpression {
    int getPriority();
}
