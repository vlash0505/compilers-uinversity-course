package org.knu.compilers.lab1.machines;

import org.knu.compilers.lab1.TokenType;

public class WhitespaceStateMachine implements StateMachine {

    private enum State {
        INITIAL, WHITESPACE
    }

    private State state = State.INITIAL;

    @Override
    public boolean next(char c) {
        switch (state) {
            case INITIAL -> {
                if (Character.isWhitespace(c)) {
                    state = State.WHITESPACE;
                    return true;
                } else {
                    return false;
                }
            }
            case WHITESPACE -> {
                return Character.isWhitespace(c);
            }
            default -> {
                return false;
            }
        }
    }

    @Override
    public boolean isAccepted() {
        return state == State.WHITESPACE;
    }

    @Override
    public TokenType getTokenType() {
        if (isAccepted()) {
            return TokenType.WHITESPACE;
        } else {
            return TokenType.LEXICAL_ERROR;
        }
    }

    @Override
    public void reset() {
        state = State.INITIAL;
    }
}
