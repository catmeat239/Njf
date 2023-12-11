package expression;

import java.math.BigDecimal;

public class Conjunct extends BinaryExpression {
    public Conjunct(SomeExpression expression1, SomeExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected String getSign() {
        return "&";
    }

    @Override
    protected int getResult(int x, int y) {
        return x & y;
    }

    @Override
    protected BigDecimal getResult(BigDecimal x, BigDecimal y) {
        throw new IllegalCallerException("BigDecimal doesn't support bitwise conjunction");
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
    public int getPriority() {
        return -10000;
    }
}
