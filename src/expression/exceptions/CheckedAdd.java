package expression.exceptions;

import expression.Add;
import expression.SomeExpression;

public class CheckedAdd extends Add {

    public CheckedAdd(SomeExpression expression1, SomeExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected int getResult(int x, int y) {
        if (x + y != (long) x + y) {
            throw new OverflowEvaluateException(x + getSign() + y);
        }
        return super.getResult(x, y);
    }
}
