package expression.parser;

public class StringCharSource implements CharSource {
    private final String string;
    private int pos = 0;

    public StringCharSource(String string) {
        this.string = string;
    }

}
