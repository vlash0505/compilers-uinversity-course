package org.knu.compilers.lab2;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.knu.compilers.lab2.gen.JavaScriptLexer;
import org.knu.compilers.lab2.gen.JavaScriptParser;

import java.io.IOException;
import java.io.InputStream;

public class ParserLabDriver {

    public static void main(String[] args) throws IOException {
        //detailed info can be viewed through the debug view.
        //Parse tree can be viewed in the ANTLR preview tool
        //with accordance to the specified grammar.

        String inputFileName = "sample_input.js";
        InputStream inputStream = ParserLabDriver.class.getClassLoader().getResourceAsStream(inputFileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + inputFileName);
        }

        CharStream charStream = CharStreams.fromStream(inputStream);
        JavaScriptLexer javaScriptLexer = new JavaScriptLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(javaScriptLexer);
        JavaScriptParser javaScriptParser = new JavaScriptParser(commonTokenStream);
        ParseTree parseTree = javaScriptParser.statement();

        System.out.println("end mark");
    }
}
