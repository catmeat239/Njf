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
        int result = super.getResult(x, y);
        if (y < 0 && result < x || y > 0 && result > x) {
            throw new OverflowEvaluateException(x + getSign() + y);
        }
        return result;
    }
}
