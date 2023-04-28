package org.knu.compilers.lab1.machines;

import org.knu.compilers.lab1.TokenType;

public class LiteralStateMachine implements StateMachine {

    private enum State {
        INITIAL, STRING, STRING_ESCAPE, INTEGER, DECIMAL_POINT, FRACTIONAL, EXPONENT_SIGN, EXPONENT, ACCEPTED
    }

    private State state = State.INITIAL;

    @Override
    public boolean next(char c) {
        switch (state) {
            case INITIAL -> {
                if (c == '\"') {
                    state = State.STRING;
                } else if (Character.isDigit(c)) {
                    state = State.INTEGER;
                } else {
                    return false;
                }
            }
            case STRING -> {
                if (c == '\"') {
                    state = State.ACCEPTED;
                } else if (c == '\\') {
                    state = State.STRING_ESCAPE;
                }
            }
            case STRING_ESCAPE -> state = State.STRING;
            case INTEGER -> {
                if (c == '.') {
                    state = State.DECIMAL_POINT;
                } else if (c == 'e' || c == 'E') {
                    state = State.EXPONENT_SIGN;
                } else if (!Character.isDigit(c)) {
                    return false;
                }
            }
            case DECIMAL_POINT -> {
                if (Character.isDigit(c)) {
                    state = State.FRACTIONAL;
                } else {
                    return false;
                }
            }
            case FRACTIONAL -> {
                if (c == 'e' || c == 'E') {
                    state = State.EXPONENT_SIGN;
                } else if (!Character.isDigit(c)) {
                    return false;
                }
            }
            case EXPONENT_SIGN -> {
                if (c == '+' || c == '-' || Character.isDigit(c)) {
                    state = State.EXPONENT;
                } else {
                    return false;
                }
            }
            case EXPONENT -> {
                if (!Character.isDigit(c)) {
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
        return state == State.ACCEPTED || state == State.INTEGER || state == State.FRACTIONAL || state == State.EXPONENT;
    }

    @Override
    public TokenType getTokenType() {
        return switch (state) {
            case ACCEPTED -> TokenType.STRING_LITERAL;
            case INTEGER -> TokenType.INTEGER_LITERAL;
            case FRACTIONAL, EXPONENT -> TokenType.FLOATING_POINT_LITERAL;
            default -> TokenType.LEXICAL_ERROR;
        };
    }

    @Override
    public void reset() {
        state = State.INITIAL;
    }
}
