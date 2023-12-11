package expression.parser;

public class StringCharSource implements CharSource {
    private final String string;
    private int pos = 0;

    public StringCharSource(String string) {
        this.string = string;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public char next() {
        return 0;
    }

    @Override
    public IllegalArgumentException error(String message) {
        return null;
    }
}
