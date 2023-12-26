package expression.exceptions;

import expression.Multiply;
import expression.SomeExpression;
import expression.TripleExpression;
import expression.Variable;

public class CheckedMultiply extends Multiply {

    public CheckedMultiply(SomeExpression expression1, SomeExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected int getResult(int x, int y) {
        int result = super.getResult(x, y);
        // result + k * 2^32 == x * y   // k == 0 ?
        // result / x + k * 2 ^ 32 / x == y
        if (x != 0 && result / x != y || result == Integer.MIN_VALUE && x == -1) {
            throw new OverflowEvaluateException(x + getSign() + y);
        }
        return result;
    }
}
