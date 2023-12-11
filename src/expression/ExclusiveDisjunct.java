package expression;

import java.math.BigDecimal;

public class ExclusiveDisjunct extends BinaryExpression {
    public ExclusiveDisjunct(SomeExpression expression1, SomeExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected String getSign() {
        return "^";
    }

    @Override
    protected int getResult(int x, int y) {
        return x | y;
    }

    @Override
    protected BigDecimal getResult(BigDecimal x, BigDecimal y) {
        throw new IllegalCallerException("BigDecimal doesn't support bitwise exclusive disjunction");
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
        return -20000;
    }
}
