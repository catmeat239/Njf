package expression.exceptions;

import expression.Negate;
import expression.SomeExpression;
import expression.TripleExpression;
import expression.Variable;

public class CheckedNegate extends Negate {
    public CheckedNegate(SomeExpression expression) {
        super(expression);
    }

    @Override
    protected int getResult(int x) {
        if (-x != -(long)x) {
            throw new OverflowEvaluateException(getSign() + x);
        }
        return super.getResult(x);
    }
}
