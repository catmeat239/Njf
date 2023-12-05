package expression;


import java.math.BigDecimal;
import java.util.Objects;

public class Const extends NullaryExpression {
    private final Object c;

    public Const(BigDecimal c) {
        this.c = c;
    }
    public Const(int c) {
        this.c = c;
    }

    @Override
    public int evaluate(int parameter) {
        return (int) c;
    }
    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return (BigDecimal) c;
    }
    @Override
    public String toString() {
        return String.valueOf(c);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && this.getClass() == obj.getClass()) {
            Const that = (Const) obj;
            return this.c.equals(that.c);
        }
        return false;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int)c;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(c);
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
