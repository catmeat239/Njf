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
        if ((long) x * y != x * y) {
            throw new OverflowEvaluateException(x + getSign() + y);
        }
        return super.getResult(x, y);
    }
}
