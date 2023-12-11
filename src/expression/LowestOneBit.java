package expression;

import java.math.BigDecimal;

public class LowestOneBit extends UnaryExpression {

    protected LowestOneBit(SomeExpression expression) {
        super(expression);
    }

    @Override
    protected int getResult(int x) {
        return Integer.lowestOneBit(x);
    }

    @Override
    protected BigDecimal getResult(BigDecimal x) {
        throw new IllegalCallerException("BigDecimal doesn't support lowestOneBit operation");
    }

    @Override
    protected String getSign() {
        return "low";
    }
}
