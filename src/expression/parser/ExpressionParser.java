package expression.parser;

import expression.*;

public class ExpressionParser implements TripleParser {


    @Override
    public TripleExpression parse(String expression) {
        return new RealParser(new StringCharSource(expression)).parseExpression();
    }

    private static class RealParser extends BaseParser {

        public RealParser(CharSource source) {
            super(source);
        }

        public SomeExpression parseExpression() {
            skipWhitespaces();
            final SomeExpression expression = parseDisjunct();
            skipWhitespaces();
            return expression;
        }


        private SomeExpression parseDisjunct() {
            SomeExpression expression = parseExclusiveDisjunct();
            while (true) {
                skipWhitespaces();
                if (take('|')) {
                    expression = new Disjunct(expression, parseExclusiveDisjunct());
                } else {
                    return expression;
                }
            }
        }

        private SomeExpression parseExclusiveDisjunct() {
            SomeExpression expression = parseConjunct();
            while (true) {
                skipWhitespaces();
                if (take('^')) {
                    expression = new ExclusiveDisjunct(expression, parseConjunct());
                } else {
                    return expression;
                }
            }
        }

        private SomeExpression parseConjunct() {
            SomeExpression expression = parseAddSubtract();

            while (true) {
                skipWhitespaces();
                if (take('&')) {
                    expression = new Conjunct(expression, parseAddSubtract());
                } else {
                    return expression;
                }
            }
        }

        private SomeExpression parseAddSubtract() {
            SomeExpression expression = parseDivideMultiply();
            while (true) {
                skipWhitespaces();
                if (take('+')) {
                    expression = new Add(expression, parseDivideMultiply());
                } else if (take('-')) {
                    expression = new Subtract(expression, parseDivideMultiply());
                } else {
                    return expression;
                }
            }
        }

        private SomeExpression parseDivideMultiply() {
            SomeExpression expression = parseUnaryExpression();
            while (true) {
                skipWhitespaces();
                if (take('/')) {
                    expression = new Divide(expression, parseUnaryExpression());
                } else if (take('*')) {
                    expression = new Multiply(expression, parseUnaryExpression());
                } else {
                    return expression;
                }
            }
        }

        private SomeExpression parseUnaryExpression() {
            skipWhitespaces();
            if (take('-')) {
                return parseMinus();
            } else if (take('h')) {
                return parseHighestOneBit();
            } else if (take('t')) {
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
            expect('1');
            return new NumberOfTrailingOnes(parseUnaryExpression());
        }

        private SomeExpression parseLowestOneBit() {
            expect("ow");
            skipWhitespaces();
            return new LowestOneBit(parseUnaryExpression());
        }

        private SomeExpression parseHighestOneBit() {
            expect("igh");
            skipWhitespaces();
            return new HighestOneBit(parseUnaryExpression());
        }

        private SomeExpression parseMinus() {
            if (test(Character::isDigit)) {
                return new Const(parseConst(true));
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
                return new Const(parseConst(false));
            }
        }

        private SomeExpression parseBraces() {
            final SomeExpression result = parseExpression();
            expect(')');
            return result;
        }

        private int parseConst(boolean isInverted) {
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
                return Integer.parseInt(sb.toString());
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
