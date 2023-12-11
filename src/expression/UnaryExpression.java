package expression;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class UnaryExpression implements SomeExpression {
    private final SomeExpression expression;

    protected UnaryExpression(SomeExpression expression) {
        this.expression = expression;
    }

    protected abstract String getSign();

    protected abstract int getResult(int x);

    protected abstract BigDecimal getResult(BigDecimal x);

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return getResult(expression.evaluate(x));
    }

    @Override
    public int evaluate(int x) {
        return getResult(expression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return getResult(expression.evaluate(x, y, z));
    }

    @Override
    public String toMiniString() {
        if (expression.getPriority() <= this.getPriority()) {
            return getSign() + " (" + expression.toMiniString() + ")";
        } else {
            return getSign() + " " + expression.toMiniString();
        }
    }

    @Override
    public String toString() {
        return getSign() + " " + expression.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && this.getClass() == obj.getClass()) {
            UnaryExpression that = (UnaryExpression) obj;
            return this.expression.equals(that.expression);
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(expression.hashCode(), this.getSign());
    }
    @Override
    public int getPriority() {
        return -100;
    }

}
