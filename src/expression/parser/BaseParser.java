package expression.parser;

public class BaseParser {
    private static final char END = 0;
    private final CharSource source;
    private char ch;
    private char pred;

    protected BaseParser(final CharSource source) {
        this.source = source;
        take();
    }

    protected char take() {
        pred = ch;
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean test(final Expectable expectable) {
        return expectable.expected(ch);
    }

    protected boolean take(final char expected) {
        return take((char c) -> c == expected);
    }

    protected boolean take(final String expected) {
        if (take(expected.charAt(0))) {
            expect(expected.substring(1));
            return true;
        }
        return false;
    }

    protected boolean take(final Expectable expectable) {
        if (expectable.expected(ch)) {
            take();
            return true;
        }
        return false;
    }

    protected void expect(final char expected) {
        if (!take(expected)) {
            throw error("Expected '" + expected + "', found '" + ch + "'");
        }
    }

    protected void expect(final String value) {
        for (final char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected boolean eof() {
        return take(END);
    }

    protected IllegalArgumentException error(final String message) {
        return source.error(message);
    }
    protected boolean checkPred(final Expectable expectable) {
        return expectable.expected(pred);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}
