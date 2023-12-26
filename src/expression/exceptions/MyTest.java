package expression.exceptions;


public class MyTest {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        System.out.println(parser.parse("((z min x)min x)\n\n"));
        System.out.println(parser.parse("((0)min0)\n").evaluate(0,0,0));
        System.out.println(parser.parse("(((x+-1)min y)min z)\n").toMiniString());
        System.out.println(parser.parse("(0 \t \u000B>\u2029\t\u2029+ 0)\n").evaluate(0,0,0));
        // System.out.println(parser.parse("(0))").toMiniString());
        // System.out.println(parser.parse("-(\u2029 \u2029\u2029-2147483648)").evaluate(1,1,1));
        System.out.println(parser.parse("1000000*x*x*x*x*x/(x-1)").evaluate(5,0,0));

        //System.out.println(parser.parse("(0))").toMiniString());
    }
}
