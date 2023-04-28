package org.knu.compilers.lab1.machines;

import org.knu.compilers.lab1.TokenType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KeywordStateMachine extends IdentifierStateMachine {
    private static final Set<String> keywordSet = new HashSet<>(Arrays.asList(
            "abstract", "assert", "boolean", "break", "byte",
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
            "void", "volatile",
            "while"
    ));

    @Override
    public boolean isAccepted() {
        return super.isAccepted() && keywordSet.contains(getIdentifier());
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.KEYWORD;
    }
}
