package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.CharSource;
import expression.parser.NoSuchOperationParserException;
import expression.parser.StringCharSource;

public class ExpressionParser implements TripleParser {


    @Override
    public TripleExpression parse(String expression) {
        return new RealParser(new StringCharSource(expression)).parse();
    }

    private static class RealParser extends BaseParser {

        private static final String[][] SIGN_OF_BINARY_EXPRESSION = {
                {"*", "/"},
                {"+", "-"},
                {"min", "max"},
                {">>>", "<<", ">>"}
        };
        private static final boolean[] NEED_WHITESPACES_FOR_BINARY_EXPRESSION = {
                false,
                false,
                true,
                false
        };
        public RealParser(CharSource source) {
            super(source);
        }

        public SomeExpression parse() {
            final SomeExpression result = parseExpression();
            if (!eof()) {
                throw new NoEOFParserException("found " + take() + " after the end of Expression");
            }
            return result;
        }

        public SomeExpression parseExpression() {
            skipWhitespaces();
            final SomeExpression expression = parseBinaryExpression(SIGN_OF_BINARY_EXPRESSION.length - 1);
            skipWhitespaces();
            return expression;
        }

        private SomeExpression createBinaryExpression(SomeExpression expression1, SomeExpression expression2, String op) {
            return switch (op) {
                case "/" -> new CheckedDivide(expression1, expression2);
                case "*" -> new CheckedMultiply(expression1, expression2);
                case "+" -> new CheckedAdd(expression1, expression2);
                case "-" -> new CheckedSubtract(expression1, expression2);
                case "min" -> new Min(expression1, expression2);
                case "max" -> new Max(expression1, expression2);
                case "<<" -> new ShiftL(expression1, expression2);
                case ">>" -> new ShiftR(expression1, expression2);
                case ">>>" -> new ArithmeticShiftR(expression1, expression2);
                default -> throw new NoSuchOperationParserException("no such op: " + op);
            };
        }

        private SomeExpression parseBinaryExpression(int i) {
            if (i < 0) {
                return parseUnaryExpression();
            }
            SomeExpression expression1 = parseBinaryExpression(i - 1);
            if (expression1 == null) {
                throw new NoFirstArgumentException();
            }
            while (true) {
                boolean wasWhitespace = checkPred((ch) -> ch == ')' || Character.isWhitespace(ch));
                skipWhitespaces();
                String op = takeOneOfSorted(SIGN_OF_BINARY_EXPRESSION[i]);
                if (op == null) {
                    return expression1;
                } else {
                    if ((!wasWhitespace) && NEED_WHITESPACES_FOR_BINARY_EXPRESSION[i]) {
                        throw new NoSeparatorParserException("Should be a whitespace or a ) before " + op);
                    }
                    SomeExpression expression2 = parseBinaryExpression(i - 1);
                    if (expression2 == null) {
                        throw new NoSecondArgumentException();
                    }
                    expression1 = createBinaryExpression(expression1, expression2, op);
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
            SomeExpression expression = parseUnaryExpression();
            if (expression == null) {
                throw new NoArgumentParserException();
            }
            return new NumberOfLeadingOnes(expression);
        }

        private SomeExpression parseNumberOfTrailingOnesO() {
            SomeExpression expression = parseUnaryExpression();
            if (expression == null) {
                throw new NoArgumentParserException();
            }
            return new NumberOfTrailingOnes(expression);
        }

        private SomeExpression parseLowestOneBit() {
            expect("ow");
            skipWhitespaces();
            SomeExpression expression = parseUnaryExpression();
            if (expression == null) {
                throw new NoArgumentParserException();
            }
            return new LowestOneBit(expression);
        }

        private SomeExpression parseHighestOneBit() {
            skipWhitespaces();
            SomeExpression expression = parseUnaryExpression();
            if (expression == null) {
                throw new NoArgumentParserException();
            }
            return new HighestOneBit(expression);
        }

        private SomeExpression parseMinus() {
            if (test(Character::isDigit)) {
                return parseConst(true);
            } else {
                SomeExpression expression = parseUnaryExpression();
                if (expression == null) {
                    throw new NoArgumentParserException();
                }
                return new CheckedNegate(expression);
            }
        }

        private SomeExpression parseNullaryExpression() {
            // skipWhitespaces();
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
            if (sb.isEmpty()) {
                return null;
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
        protected String takeOneOfSorted(final String[] expected) {
            // for all i < n - 1
            // expected[i].length() >= expected[i + 1].length()
            StringBuilder sb = new StringBuilder();
            findStringLoop:
            for (String s : expected) {
                int j = 0;
                while (j < sb.length()) {
                    if (sb.charAt(j) != s.charAt(j)) {
                        continue findStringLoop;
                    }
                    j++;
                }
                while (j < s.length()) {
                    if (take(s.charAt(j))) {
                        sb.append(s.charAt(j));
                    } else {
                        continue findStringLoop;
                    }
                    j++;
                }
                // string found
                return s;
            }
            if (!sb.isEmpty()) {
                throw new NoSuchOperationParserException(sb.toString());
            }
            return null;
        }

    }
}
