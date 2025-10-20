package calculator;

public enum DelimiterHeader {
    PREFIX("//"),
    SUFFIX("\\n");

    private String value;

    DelimiterHeader(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
