package expression;

public abstract class UnaryExpression implements SomeExpression {
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
