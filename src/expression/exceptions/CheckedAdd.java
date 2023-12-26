package expression.exceptions;

import expression.Add;
import expression.SomeExpression;

public class CheckedAdd extends Add {

    public CheckedAdd(SomeExpression expression1, SomeExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected int getResult(int x, int y) {
        int result = super.getResult(x, y);
        if (y > 0 && result < x || y < 0 && result > x) {
            throw new OverflowEvaluateException(x + getSign() + y);
        }
        return result;
    }
}
