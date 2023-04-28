package org.knu.compilers;

import org.knu.compilers.lab1.Lexer;
import org.knu.compilers.lab1.Token;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String input =
                "const string1 = \"A string primitive\";" +
                "const string2 = new String(\"A String object\");" +
                "console.log(\"It's a text\");" +
                "function add(a, b) {\n" +
                "  return a + b;\n" +
                "}\n";
        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.tokenize();
        tokens.forEach(System.out::println);
    }
}