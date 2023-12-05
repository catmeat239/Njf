package expression;

import java.math.BigDecimal;

public class Add extends BinaryExpression {

    public Add(SomeExpression expression1, SomeExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected String getSign() {
        return "+";
    }

    @Override
    protected boolean isAssociativeWithOthers() {
        return true;
    }

    @Override
    protected boolean isAssociative() {
        return true;
    }

    @Override
    protected int getResult(int x, int y) {
        return x + y;
    }

    @Override
    protected BigDecimal getResult(BigDecimal x, BigDecimal y) {
        return x.add(y);
    }

    @Override
    public int getPriority() {
        return 2000;
    }
}
