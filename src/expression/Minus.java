package expression;

import java.math.BigDecimal;

public class Minus extends UnaryExpression {

    private final SomeExpression expression;

    public Minus(SomeExpression expression) {
        this.expression = expression;
    }


    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return expression.evaluate(x).negate();
    }

    @Override
    public int evaluate(int x) {
        return -expression.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -expression.evaluate(x, y, z);
    }
}
