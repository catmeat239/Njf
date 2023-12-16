package expression.parser;

import expression.*;

public class ExpressionParser implements TripleParser {


    @Override
    public TripleExpression parse(String expression) {
        return new RealParser(new StringCharSource(expression)).parse();
    }

    private static class RealParser extends BaseParser {

        private static final String[][] SIGN = {
                {"*", "/"},
                {"+", "-"},
                {"&"},
                {"^"},
                {"|"}
        };

        public RealParser(CharSource source) {
            super(source);
        }

        public SomeExpression parse() {
            final SomeExpression result = parseExpression();
            if (!eof()) {
                throw error("Expected eof");
            }
            return result;
        }

        public SomeExpression parseExpression() {
            skipWhitespaces();
            final SomeExpression expression = parseBinaryExpression(SIGN.length - 1);
            skipWhitespaces();
            return expression;
        }
        private SomeExpression createBinaryExpression(SomeExpression expression1, SomeExpression expression2, String op) {
            return switch (op) {
                case "/" -> new Divide(expression1, expression2);
                case "*" -> new Multiply(expression1, expression2);
                case "+" -> new Add(expression1, expression2);
                case "-" -> new Subtract(expression1, expression2);
                case "&" -> new Conjunct(expression1, expression2);
                case "^" -> new ExclusiveDisjunct(expression1, expression2);
                case "|" -> new Disjunct(expression1, expression2);

                default -> throw error("No such op: " + op);
            };
        }

        private SomeExpression parseBinaryExpression(int i) {
            if (i < 0) {
                return parseUnaryExpression();
            }
            SomeExpression expression1 = parseBinaryExpression(i - 1);
            while (true) {
                skipWhitespaces();
                boolean foundSecondExpression = false;
                for (int j = 0; j < SIGN[i].length; j++) {
                    if (take(SIGN[i][j])) {
                        expression1 = createBinaryExpression(expression1,
                                parseBinaryExpression(i - 1),
                                SIGN[i][j]);
                        foundSecondExpression = true;
                        break;
                    }
                }
                if (!foundSecondExpression) {
                    return expression1;
                }
            }
        }

        private SomeExpression parseUnaryExpression() {
            skipWhitespaces();
            if (take('-')) {
                return parseMinus();
            } else if (take("high")) {
                return parseHighestOneBit();
            } else if (take("t1")) {
                return parseNumberOfTrailingOnesO();
            } else if (take('l')) {
                if (take('1')) {
                    return parseNumberOfLeadingZeroes();
                } else {
                    return parseLowestOneBit();
                }
            } else {
                return parseNullaryExpression();
            }
        }

        private SomeExpression parseNumberOfLeadingZeroes() {
            skipWhitespaces();
            return new NumberOfLeadingOnes(parseUnaryExpression());
        }

        private SomeExpression parseNumberOfTrailingOnesO() {
            return new NumberOfTrailingOnes(parseUnaryExpression());
        }

        private SomeExpression parseLowestOneBit() {
            expect("ow");
            skipWhitespaces();
            return new LowestOneBit(parseUnaryExpression());
        }

        private SomeExpression parseHighestOneBit() {
            skipWhitespaces();
            return new HighestOneBit(parseUnaryExpression());
        }

        private SomeExpression parseMinus() {
            if (test(Character::isDigit)) {
                return parseConst(true);
            } else {
                skipWhitespaces();
                return new Negate(parseUnaryExpression());
            }
        }

        private SomeExpression parseNullaryExpression() {
            skipWhitespaces();
            if (take('(')) {
                return parseBraces();
            } else if (take('x')) {
                return new Variable("x");
            } else if (take('y')) {
                return new Variable("y");
            } else if (take('z')) {
                return new Variable("z");
            } else {
                return parseConst(false);
            }
        }

        private SomeExpression parseBraces() {
            final SomeExpression result = parseExpression();
            expect(')');
            return result;
        }

        private SomeExpression parseConst(boolean isInverted) {
            final StringBuilder sb = new StringBuilder();
            if (isInverted) {
                sb.append('-');
            }
            if (take('-')) {
                sb.append('-');
            }
            while (test(Character::isDigit)) {
                sb.append(take());
            }
            try {
                return new Const(Integer.parseInt(sb.toString()));
            } catch (NumberFormatException e) {
                throw error("Not parseable integer: " + "'" + sb + "'");
            }
        }

        private void skipWhitespaces() {
            while (take(Character::isWhitespace)) {

            }
        }
    }
}
