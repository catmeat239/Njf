package expression;

import java.math.BigDecimal;

public class Multiply extends BinaryExpression {

    public Multiply(SomeExpression expression1, SomeExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected String getSign() {
        return "*";
    }

    @Override
    protected boolean isAssociativeWithOthers() {
        return false;
    }

    @Override
    protected boolean isAssociative() {
        return true;
    }

    @Override
    protected int getResult(int x, int y) {
        return x * y;
    }

    @Override
    protected BigDecimal getResult(BigDecimal x, BigDecimal y) {
        return x.multiply(y);
    }

    @Override
    public int getPriority() {
        return 1000;
    }
}
