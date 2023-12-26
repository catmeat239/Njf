package expression.exceptions;

import expression.Divide;
import expression.SomeExpression;

public class CheckedDivide extends Divide {

    public CheckedDivide(SomeExpression expression1, SomeExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected int getResult(int x, int y) {
        if (y == 0) {
             throw new DivisionByZeroEvaluateException(x + getSign() + y);
        }
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowEvaluateException(x + getSign() + y);
        }
        return super.getResult(x, y);
    }
}
