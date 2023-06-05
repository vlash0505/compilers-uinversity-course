package org.knu.compilers.lab1;

import java.io.*;
import java.net.URL;
import java.util.List;

public class LexerEntryPoint {
    public final static String OUTPUT_DIRECTORY = "output";
    public final static String OUTPUT_FILENAME = "tokens.txt";

    public static void main(String[] args) {
        String inputFileName = "sample_input.js";
        String input = readResourceFileToString(inputFileName);

        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.tokenize();

        String tokensSequence = buildTokensSequence(tokens);
        createOutputFileRelativeToClass(tokensSequence);
    }

    private static String buildTokensSequence(List<Token> tokens) {
        StringBuilder output = new StringBuilder();
        tokens.forEach(e -> output.append(e).append("\n"));

        return output.toString();
    }

    private static String readResourceFileToString(String fileName) {
        InputStream inputStream = LexerEntryPoint.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }

        try (
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }

            return stringBuilder.toString().trim();

        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }

    private static void createOutputFileRelativeToClass(String content) {
        URL classFileUrl = LexerEntryPoint.class.getResource(LexerEntryPoint.class.getSimpleName() + ".class");
        if (classFileUrl == null) {
            throw new IllegalArgumentException("Class file not found");
        }

        File classFileDirectory = new File(classFileUrl.getPath()).getParentFile();
        File outputDirectory = new File(classFileDirectory, OUTPUT_DIRECTORY);
        if (!outputDirectory.exists()) {
            if (!outputDirectory.mkdir()) {
                throw new RuntimeException("Error creating directory: " + outputDirectory.getAbsolutePath());
            }
        }

        File outputFile = new File(outputDirectory, OUTPUT_FILENAME);

        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            fileWriter.write(content);
            System.out.println("File with tokens sequence created: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Error writing file: " + OUTPUT_FILENAME, e);
        }
    }
}
