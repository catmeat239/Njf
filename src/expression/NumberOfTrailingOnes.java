package expression;

import java.math.BigDecimal;

public class NumberOfTrailingOnes extends UnaryExpression{
    public NumberOfTrailingOnes(SomeExpression expression) {
        super(expression);
    }

    @Override
    protected int getResult(int x) {
        return Integer.numberOfTrailingZeros(~x);
    }

    @Override
    protected BigDecimal getResult(BigDecimal x) {
        throw new IllegalCallerException("BigDecimal doesn't support NumberOfTrailingOnes operation");
    }

    @Override
    protected String getSign() {
        return "t1";
    }
}
