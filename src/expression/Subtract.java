package expression;

import java.math.BigDecimal;

public class Subtract extends BinaryExpression {
    public Subtract(SomeExpression expression1, SomeExpression expression2) {
        super(expression1, expression2);
    }


    @Override
    protected String getSign() {
        return "-";
    }

    @Override
    protected int getResult(int x, int y) {
        return x - y;
    }

    @Override
    protected BigDecimal getResult(BigDecimal x, BigDecimal y) {
        return x.subtract(y);
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
        return 2000;
    }
}