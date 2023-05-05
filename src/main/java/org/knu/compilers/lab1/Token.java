package org.knu.compilers.lab1;

public class Token {
    private final TokenType type;
    private final String value;
    private final int position;

    public Token(TokenType type, String value, int position) {
        this.type = type;
        this.value = value;
        this.position = position;
    }

    public String toString() {
        return "Token {\n" +
                "type = " + type + "\n" +
                ", value = '" + value + "'\n" +
                "} at position = " + position + "\n";
    }
}
