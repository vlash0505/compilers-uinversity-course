package org.knu.compilers.lab1.machines;

import org.knu.compilers.lab1.TokenType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KeywordStateMachine implements StateMachine {
    private enum State {
        INITIAL, PREFIX, ACCEPTED
    }

    private static final Set<String> KEYWORDS = new HashSet<>(Arrays.asList(
            "abstract", "assert", "async",
            "boolean", "break", "byte",
            "case", "catch", "char", "class", "const", "console", "continue",
            "default", "do", "document", "double",
            "else", "enum", "extends",
            "false", "function", "final", "finally", "float", "for",
            "goto",
            "if", "implements", "import", "instanceof", "int", "interface",
            "long", "let", "log",
            "null", "native", "new",
            "package", "private", "protected", "public",
            "return",
            "short", "static", "strictfp", "super", "switch", "synchronized", "String",
            "true", "this", "throw", "throws", "transient", "try",
            "var", "void", "volatile",
            "while"
    ));

    private State state = State.INITIAL;
    private final StringBuilder buffer = new StringBuilder();

    @Override
    public boolean next(char c) {
        buffer.append(c);
        String currentBuffer = buffer.toString();

        switch (state) {
            case INITIAL -> {
                if (isPrefix(currentBuffer)) {
                    state = State.PREFIX;
                    return true;
                }
            }
            case PREFIX -> {
                if (isPrefix(currentBuffer)) {
                    state = State.PREFIX;
                    return true;
                } else if (!Character.isLetter(c) && KEYWORDS.contains(currentBuffer.substring(0, currentBuffer.length() - 1))) {
                    state = State.ACCEPTED;
                }
            }
        }
        return false;
    }

    private boolean isPrefix(String prefix) {
        return KEYWORDS.stream().anyMatch(op -> op.startsWith(prefix));
    }

    @Override
    public boolean isAccepted() {
        return state == State.ACCEPTED;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.KEYWORD;
    }

    @Override
    public void reset() {
        state = State.INITIAL;
        buffer.setLength(0);
    }
}
