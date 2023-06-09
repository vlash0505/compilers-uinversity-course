package org.knu.compilers.lab1.machines;

import org.knu.compilers.lab1.TokenType;

public class IdentifierStateMachine implements StateMachine {

    private enum State {
        INITIAL, LETTER, DIGIT_OR_LETTER, ACCEPTED
    }

    private State currentState;

    public IdentifierStateMachine() {
        currentState = State.INITIAL;
    }

    @Override
    public boolean next(char c) {
        switch (currentState) {
            case INITIAL -> {
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

    @Override
    public void reset() {
        currentState = State.INITIAL;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.IDENTIFIER;
    }
}
