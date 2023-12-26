package expression.exceptions;

import expression.BinaryExpression;
import expression.SomeExpression;

import java.math.BigDecimal;

public class Min extends BinaryExpression {
    public Min(SomeExpression expression1, SomeExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected String getSign() {
        return "min";
    }

    @Override
    protected int getResult(int x, int y) {
        return Math.min(x, y);
    }

    @Override
    protected BigDecimal getResult(BigDecimal x, BigDecimal y) {
        return x.min(y);
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
