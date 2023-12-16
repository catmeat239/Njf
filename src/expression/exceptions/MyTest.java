package expression.exceptions;


public class MyTest {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        System.out.println(parser.parse("(0))").toMiniString());
    }
}
