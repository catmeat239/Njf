package expression.exceptions;


public class MyTest {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        System.out.println(parser.parse("-(\u2029 \u2029\u2029-2147483648)").evaluate(1,1,1));
        //System.out.println(parser.parse("(0))").toMiniString());
    }
}
