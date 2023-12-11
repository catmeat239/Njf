package expression;

import java.math.BigDecimal;
import java.util.Objects;

public class Variable extends NullaryExpression implements SomeExpression {
    private final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public int evaluate(int parameter) {
        return parameter;
    }
    @Override
    public BigDecimal evaluate(BigDecimal parameter) {
        return parameter;
    }

    @Override
    public String toString() {
        return variable;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && this.getClass() == obj.getClass()) {
            Variable that = (Variable) obj;
            return Objects.equals(this.variable, that.variable);
        }
        return false;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (variable) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new IllegalArgumentException("No such argument: " + variable);
        };
    }

    @Override
    public int getPriority() {
        return 0;
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(variable);
    }
}
