package org.knu.compilers.lab1.machines;

import org.knu.compilers.lab1.TokenType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KeywordStateMachine implements StateMachine {
    private static final Set<String> KEYWORDS = new HashSet<>(Arrays.asList(
            "abstract", "assert", "async",
            "boolean", "break", "byte",
            "case", "catch", "char", "class", "const", "console", "continue",
            "default", "do", "double",
            "else", "enum", "extends",
            "false", "function", "final", "finally", "float", "for",
            "goto",
            "if", "implements", "import", "instanceof", "int", "interface",
            "long", "let", "log",
            "null", "native", "new",
            "package", "private", "protected", "public",
            "return",
            "short", "static", "strictfp", "super", "switch", "synchronized",
            "true", "this", "throw", "throws", "transient", "try",
            "var", "void", "volatile",
            "while"
    ));

    private final StringBuilder buffer = new StringBuilder();
    private boolean isAccepted = false;
    private boolean hasReachedEnd = false;

    @Override
    public boolean next(char c) {
        if (hasReachedEnd) {
            return false;
        }

        buffer.append(c);
        String currentOperatorStr = buffer.toString();

        boolean isPrefix = KEYWORDS.stream().anyMatch(op -> op.startsWith(currentOperatorStr));

        if (!isPrefix) {
            buffer.setLength(buffer.length() - 1);
            hasReachedEnd = true;
            return false;
        }

        isAccepted = KEYWORDS.contains(currentOperatorStr);
        return true;
    }

    @Override
    public boolean isAccepted() {
        return isAccepted;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.KEYWORD;
    }

    @Override
    public void reset() {
        buffer.setLength(0);
        isAccepted = false;
        hasReachedEnd = false;
    }
}
