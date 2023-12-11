package expression;

import java.math.BigDecimal;

public class HighestOneBit extends UnaryExpression {
    protected HighestOneBit(SomeExpression expression) {
        super(expression);
    }


    @Override
    protected String getSign() {
        return "high";
    }

    @Override
    protected int getResult(int x) {
        return Integer.highestOneBit(x);
    }

    @Override
    protected BigDecimal getResult(BigDecimal x) {
        throw new IllegalCallerException("BigDecimal doesn't support highestOneBit operation");
    }
}
