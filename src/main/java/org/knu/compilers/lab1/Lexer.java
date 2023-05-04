package org.knu.compilers.lab1;

import org.knu.compilers.lab1.machines.*;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;
    private int position;
    private final List<Token> tokens;

    private final IdentifierStateMachine identifierStateMachine;
    private final KeywordStateMachine keywordStateMachine;
    private final LiteralStateMachine literalStateMachine;
    private final OperatorStateMachine operatorStateMachine;
    private final SeparatorStateMachine separatorStateMachine;
    private final WhitespaceStateMachine whitespaceStateMachine;
    private final CommentStateMachine commentStateMachine;

    public Lexer(String input) {
        this.input = input;
        this.position = 0;
        this.tokens = new ArrayList<>();

        this.keywordStateMachine = new KeywordStateMachine();
        this.identifierStateMachine = new IdentifierStateMachine();
        this.literalStateMachine = new LiteralStateMachine();
        this.operatorStateMachine = new OperatorStateMachine();
        this.separatorStateMachine = new SeparatorStateMachine();
        this.whitespaceStateMachine = new WhitespaceStateMachine();
        this.commentStateMachine = new CommentStateMachine();
    }

    public List<Token> tokenize() {
        while (position < input.length()) {
            int startPosition = position;

            Token matchedToken = null;
            StateMachine[] stateMachines = {
                    whitespaceStateMachine,
                    keywordStateMachine,
                    identifierStateMachine,
                    literalStateMachine,
                    commentStateMachine,
                    operatorStateMachine,
                    separatorStateMachine
            };

            for (StateMachine stateMachine : stateMachines) {
                if (matchedToken == null) {
                    stateMachine.reset();
                    while (position < input.length() && stateMachine.next(input.charAt(position))) {
                        position++;
                    }
                    if (stateMachine.isAccepted()) {
                        String value = input.substring(startPosition, position);
                        matchedToken = new Token(stateMachine.getTokenType(), value, startPosition);
                    } else {
                        position = startPosition;
                    }
                }
            }

            if (matchedToken != null) {
                tokens.add(matchedToken);
            } else {
                tokens.add(new Token(TokenType.LEXICAL_ERROR, String.valueOf(input.charAt(position)), position));
                position++;
            }
        }
        tokens.add(new Token(TokenType.EOF, "", position));
        return tokens;
    }
}
