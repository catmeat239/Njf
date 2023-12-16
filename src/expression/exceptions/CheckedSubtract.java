package expression.exceptions;

import expression.SomeExpression;
import expression.Subtract;
import expression.TripleExpression;
import expression.Variable;

public class CheckedSubtract extends Subtract {

    public CheckedSubtract(SomeExpression expression1, SomeExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected int getResult(int x, int y) {
        if (x - y != (long)x - y) {
            throw new OverflowEvaluateException(x + getSign() + y);
        }
        return super.getResult(x, y);
    }
}
