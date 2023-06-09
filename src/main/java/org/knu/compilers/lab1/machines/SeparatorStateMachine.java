package org.knu.compilers.lab1.machines;

import org.knu.compilers.lab1.TokenType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SeparatorStateMachine implements StateMachine {
    private enum State {
        INITIAL, ACCEPTED
    }

    private State currentState;
    private static final Set<Character> SEPARATORS = new HashSet<>(Arrays.asList('(', ')', '{', '}', '[', ']', ';', ',', '.', ':'));

    public SeparatorStateMachine() {
        currentState = State.INITIAL;
    }

    @Override
    public boolean next(char c) {
        if (Objects.requireNonNull(currentState) == State.INITIAL) {
            if (SEPARATORS.contains(c)) {
                currentState = State.ACCEPTED;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAccepted() {
        return currentState == State.ACCEPTED;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.SEPARATOR;
    }

    @Override
    public void reset() {
        currentState = State.INITIAL;
    }
}
