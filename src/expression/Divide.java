package expression;

import java.math.BigDecimal;

public class Divide extends BinaryExpression {

    public Divide(SomeExpression expression1, SomeExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected String getSign() {
        return "/";
    }

    @Override
    protected boolean isAssociativeWithOthers() {
        return false;
    }

    @Override
    protected boolean isAssociative() {
        return false;
    }

    @Override
    protected int getResult(int x, int y) {
        return x / y;
    }

    @Override
    protected BigDecimal getResult(BigDecimal x, BigDecimal y) {
        return x.divide(y);
    }

    @Override
    public int getPriority() {
        return -1000;
    }
}
