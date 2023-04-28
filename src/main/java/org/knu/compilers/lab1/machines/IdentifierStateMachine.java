package org.knu.compilers.lab1.machines;

import org.knu.compilers.lab1.TokenType;

public class IdentifierStateMachine implements StateMachine {

    private enum State {
        START, LETTER, DIGIT_OR_LETTER, ACCEPTED
    }

    private State currentState;
    private final StringBuilder buffer = new StringBuilder();

    public IdentifierStateMachine() {
        currentState = State.START;
    }

    @Override
    public boolean next(char c) {
        buffer.append(c);
        switch (currentState) {
            case START -> {
                if (Character.isLetter(c) || c == '_') {
                    currentState = State.LETTER;
                    return true;
                }
            }
            case LETTER -> {
                if (Character.isLetterOrDigit(c) || c == '_') {
                    currentState = State.DIGIT_OR_LETTER;
                    return true;
                } else {
                    currentState = State.ACCEPTED;
                }
            }
            case DIGIT_OR_LETTER -> {
                if (Character.isLetterOrDigit(c) || c == '_') {
                    return true;
                } else {
                    currentState = State.ACCEPTED;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isAccepted() {
        return currentState == State.ACCEPTED || currentState == State.LETTER || currentState == State.DIGIT_OR_LETTER;
    }

    public String getIdentifier() {
        return buffer.substring(0, buffer.length() - 1);
    }

    @Override
    public void reset() {
        currentState = State.START;
        buffer.setLength(0);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.IDENTIFIER;
    }
}
