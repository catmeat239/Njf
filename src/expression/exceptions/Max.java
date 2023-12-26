package expression.exceptions;

import expression.BinaryExpression;
import expression.SomeExpression;

import java.math.BigDecimal;

public class Max extends BinaryExpression {
    public Max(SomeExpression expression1, SomeExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected String getSign() {
        return "max";
    }

    @Override
    protected int getResult(int x, int y) {
        return Math.max(x, y);
    }

    @Override
    protected BigDecimal getResult(BigDecimal x, BigDecimal y) {
        return x.max(y);
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
        return -200000;
    }
}
