package org.knu.compilers.lab1.machines;

import org.knu.compilers.lab1.TokenType;

public class OperatorStateMachine implements StateMachine {

    private enum State {
        INITIAL, ACCEPTED, PLUS, MINUS, ASTERISK, SLASH, PERCENT, CARET, EQUAL, LESS_THAN, GREATER_THAN, AMPERSAND, QUESTION_MARK, ARROW, DOUBLE_EQUAL, PIPE, EXCLAMATION
    }

    private State state = State.INITIAL;

    @Override
    public boolean next(char c) {
        switch (state) {
            case INITIAL -> {
                switch (c) {
                    case '+' -> state = State.PLUS;
                    case '-' -> state = State.MINUS;
                    case '*' -> state = State.ASTERISK;
                    case '/' -> state = State.SLASH;
                    case '%' -> state = State.PERCENT;
                    case '^' -> state = State.CARET;
                    case '=' -> state = State.EQUAL;
                    case '<' -> state = State.LESS_THAN;
                    case '>' -> state = State.GREATER_THAN;
                    case '&' -> state = State.AMPERSAND;
                    case '?' -> state = State.QUESTION_MARK;
                    case '|' -> state = State.PIPE;
                    case '!' -> state = State.EXCLAMATION;
                    default -> {
                        return false;
                    }
                }
            }
            case EQUAL -> {
                //covers arrow function case, can be extracted into separate token type if needed
                if (c == '=') {
                    state = State.DOUBLE_EQUAL;
                } else if (c == '>') {
                    state = State.ARROW;
                } else {
                    return false;
                }
            }
            case DOUBLE_EQUAL, ASTERISK, SLASH, PERCENT, CARET, EXCLAMATION, LESS_THAN, GREATER_THAN, AMPERSAND, PIPE, MINUS, PLUS -> {
                if (c == '=') {
                    state = State.ACCEPTED;
                } else {
                    return false;
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
        return state == State.ACCEPTED || state.ordinal() >= State.PLUS.ordinal() && state.ordinal() <= State.PIPE.ordinal();
    }

    @Override
    public TokenType getTokenType() {
        if (isAccepted()) {
            return TokenType.OPERATOR;
        } else {
            return TokenType.LEXICAL_ERROR;
        }
    }

    @Override
    public void reset() {
        state = State.INITIAL;
    }
}

