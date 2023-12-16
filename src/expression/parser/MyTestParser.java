package expression.parser;

public class MyTestParser {
    public static void main(String[] args) {

        ExpressionParser parser = new ExpressionParser();
       // System.out.println(parser.parse("(0))").toMiniString());
        // System.out.println(parser.parse("- l1((1))").toString());
        System.out.println(parser.parse("(z * (z * -(-1)))").toMiniString());
        System.out.println(parser.parse("- 0").toString());
        System.out.println(parser.parse("-5").toString());
        System.out.println(parser.parse("- 5").toString());

        System.out.println(parser.parse("x   -  y + z").toString());
        System.out.println(parser.parse("x   +  y *   5 +  z  /z | 3").toString());
        System.out.println(parser.parse("" + Integer.MAX_VALUE).toString());

        System.out.println(parser.parse("x   +  (y ^   5) *  z / z | 3").toMiniString());

    }
}
