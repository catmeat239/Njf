package expression;

import java.math.BigDecimal;

public class NumberOfLeadingOnes extends UnaryExpression {

    public NumberOfLeadingOnes(SomeExpression expression) {
        super(expression);
    }


    @Override
    protected String getSign() {
        return "l1";
    }

    @Override
    protected int getResult(int x) {
        return Integer.numberOfLeadingZeros(~x);
    }

    @Override
    protected BigDecimal getResult(BigDecimal x) {
        throw new IllegalCallerException("BigDecimal doesn't support NumberOfLeadingOnes operation");
    }
}
