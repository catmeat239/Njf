package expression.exceptions;

import expression.BinaryExpression;
import expression.SomeExpression;

import java.math.BigDecimal;

public class ShiftL extends BinaryExpression {
    public ShiftL(SomeExpression expression1, SomeExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected String getSign() {
        return "<<";
    }

    @Override
    protected int getResult(int x, int y) {
        return x << y;
    }

    @Override
    protected BigDecimal getResult(BigDecimal x, BigDecimal y) {
        throw new IllegalCallerException("BigDecimal doesn't support shift left operation");
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
    public int getPriority() {
        return -100000;
    }
}
