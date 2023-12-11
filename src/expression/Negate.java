package expression;

import java.math.BigDecimal;

public class Negate extends UnaryExpression {


    public Negate(SomeExpression expression) {
        super(expression);
    }

    @Override
    protected String getSign() {
        return "-";
    }

    @Override
    protected int getResult(int x) {
        return -x;
    }

    @Override
    protected BigDecimal getResult(BigDecimal x) {
        return x.negate();
    }

    // todo() : NOTE :NOTE implement hashcode and equals think about deletin

}
