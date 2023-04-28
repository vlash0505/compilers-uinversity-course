package org.knu.compilers.lab1.machines;

import org.knu.compilers.lab1.TokenType;

public interface StateMachine {
    boolean next(char c);
    boolean isAccepted();
    void reset();
    TokenType getTokenType();
}
