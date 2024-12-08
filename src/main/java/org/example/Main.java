package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter a text with interrogative sentences (end them with '?'):");
            String inputText = scanner.nextLine();

            System.out.println("Enter the target word length:");
            int targetLength = scanner.nextInt();

            TextProcessor textProcessor = new TextProcessor();
            textProcessor.extractUniqueWordsFromQuestions(new StringBuilder(inputText), targetLength);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
