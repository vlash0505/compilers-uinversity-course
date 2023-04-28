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

    @Override
    public String toString() {
        return "(" + type + " " + value + " at position " + position + ")";
    }
}
