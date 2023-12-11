package expression;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class BinaryExpression implements SomeExpression {

    private final SomeExpression expression1;
    private final SomeExpression expression2;

    public BinaryExpression(SomeExpression expression1, SomeExpression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    protected abstract String getSign();

    protected abstract int getResult(int x, int y);

    protected abstract BigDecimal getResult(BigDecimal x, BigDecimal y);

    @Override
    public int evaluate(int x) {
        return getResult(expression1.evaluate(x), expression2.evaluate(x));
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return getResult(expression1.evaluate(x), expression2.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return getResult(expression1.evaluate(x, y, z), expression2.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" +
                expression1.toString() + " " +
                getSign() + " " + expression2.toString() +
                ")";
    }

    private boolean needBracesOnFirstExpression() {
        return expression1.getPriority() < this.getPriority();
    }

    private boolean needBracesOnSecondExpression() {
        return expression2.getPriority() < this.getPriority() ||

                expression2.getPriority() == this.getPriority() &&
                        !(expression2 instanceof NullaryExpression) &&
                        (this.getClass() != expression2.getClass() || !this.isAssociative()) &&
                        (this.getClass() == expression2.getClass() || !this.isAssociativeWithOthers());
    }

    protected abstract boolean isAssociativeWithOthers();

    protected abstract boolean isAssociative();

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        if (needBracesOnFirstExpression()) {
            sb.append('(');
        }
        sb.append(expression1.toMiniString());
        if (needBracesOnFirstExpression()) {
            sb.append(')');
        }
        sb.append(" ");
        sb.append(getSign());
        sb.append(" ");
        if (needBracesOnSecondExpression()) {
            sb.append('(');
        }
        sb.append(expression2.toMiniString());
        if (needBracesOnSecondExpression()) {
            sb.append(')');
        }
        return String.valueOf(sb);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && this.getClass() == obj.getClass()) {
            BinaryExpression that = (BinaryExpression) obj;
            return this.expression1.equals(that.expression1)
                    && this.expression2.equals(that.expression2);
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(expression1.hashCode(), expression2.hashCode(), this.getSign());
    }
}
