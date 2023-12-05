package expression.parser;

import expression.*;

import java.text.ParseException;
import java.util.InputMismatchException;

public class ExpressionParser implements TripleParser {


    @Override
    public TripleExpression parse(String expression) {
        return new RealParser(new StringCharSource(expression)).parseExpression();
    }

    private static class RealParser extends BaseParser {

        protected RealParser(CharSource source) {
            super(source);
        }


        public TripleExpression parseExpression() {
            skipWhitespaces();
            if (take('(')) {
                TripleExpression result = parseExpression();
                expect(')');
                return result;
            } else if (take('x') || take('y') || take('z')) {
                return parseVariable();
            } else if (take('-')) {
                return parseMinus();
            } else {
                return parseInteger();
            }
        }

        private TripleExpression parseMinus() {
            if (test(Character::isDigit)) {
                return parseInteger();
            } else {
                return new Minus((SomeExpression) parseExpression());
            }
        }

        private TripleExpression parseInteger() {
            StringBuilder sb = new StringBuilder();
            while (test(Character::isDigit)) {
                sb.append(take());
            }
            TripleExpression expression1;
            try {
                expression1 = new Const(Integer.parseInt(sb.toString()));
            } catch (InputMismatchException e) {
                throw error("Not parseable integer " + sb);
            }
            skipWhitespaces();
            if (take('+')) {
                return new Add((SomeExpression) expression1, (SomeExpression)parseExpression());
            } else if (take('-')) {

            }
        }

        private TripleExpression parseVariable() {
            if (take('x')) {
                return new Variable("x");
            } else if (take('y')) {
                return new Variable("y");
            } else if (take('z')) {
                return new Variable("z");
            } else {
                throw error("parseVariable: Expected x or y or z");
            }
        }

        private void skipWhitespaces() {
            while (take(Character::isWhitespace)) {

            }
        }
    }
}
