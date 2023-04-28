package org.knu.compilers.lab1.machines;

import org.knu.compilers.lab1.TokenType;

public class CommentStateMachine implements StateMachine {

    private enum State {
        INITIAL, SLASH, ASTERISK, ASTERISK_SLASH, LINE_COMMENT, BLOCK_COMMENT
    }

    private State state = State.INITIAL;

    @Override
    public boolean next(char c) {
        switch (state) {
            case INITIAL -> {
                if (c == '/') {
                    state = State.SLASH;
                } else {
                    return false;
                }
            }
            case SLASH -> {
                if (c == '*') {
                    state = State.BLOCK_COMMENT;
                } else if (c == '/') {
                    state = State.LINE_COMMENT;
                } else {
                    return false;
                }
            }
            case LINE_COMMENT -> {
                if (c == '\n') {
                    return false;
                }
            }
            case BLOCK_COMMENT -> {
                if (c == '*') {
                    state = State.ASTERISK;
                }
            }
            case ASTERISK -> {
                if (c == '/') {
                    state = State.ASTERISK_SLASH;
                } else if (c != '*') {
                    state = State.BLOCK_COMMENT;
                }
            }
            default -> {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isAccepted() {
        return state == State.LINE_COMMENT || state == State.ASTERISK_SLASH;
    }

    @Override
    public TokenType getTokenType() {
        if (isAccepted()) {
            return TokenType.COMMENT;
        } else {
            return TokenType.LEXICAL_ERROR;
        }
    }

    @Override
    public void reset() {
        state = State.INITIAL;
    }
}
