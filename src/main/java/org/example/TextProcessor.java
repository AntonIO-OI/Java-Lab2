package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TextProcessor {

    public List<StringBuilder> splitText(StringBuilder text, String delimiter) {
        List<StringBuilder> parts = new ArrayList<>();
        int index;

        while ((index = text.indexOf(delimiter)) != -1) {
            parts.add(new StringBuilder(text.substring(0, index).trim()));
            text.delete(0, index + delimiter.length());
        }
        if (!text.isEmpty()) {
            parts.add(text);
        }
        return parts;
    }

    private boolean isValidWord(String word) {
        return word.matches("^[a-zA-Z]+$");
    }

    public void extractUniqueWordsFromQuestions(StringBuilder inputText, int wordLength) {
        try {
            List<StringBuilder> sentences = new ArrayList<>();
            int startIndex = 0;
            int endIndex;

            while (startIndex < inputText.length()) {
                endIndex = Math.min(
                        Math.min(indexOfOrLength(inputText, "?", startIndex),
                                indexOfOrLength(inputText, ".", startIndex)),
                        indexOfOrLength(inputText, "!", startIndex)
                );

                if (endIndex > startIndex) {
                    StringBuilder sentence = new StringBuilder(inputText.substring(startIndex, endIndex).trim());
                    if (endIndex < inputText.length() && inputText.charAt(endIndex) == '?') {
                        sentences.add(sentence);
                    }
                }
                startIndex = endIndex + 1;
            }

            Set<String> uniqueWords = new HashSet<>();

            for (StringBuilder sentence : sentences) {
                String[] words = sentence.toString().split("\\s+");
                for (String word : words) {
                    word = word.trim();
                    if (word.length() == wordLength && isValidWord(word)) {
                        uniqueWords.add(word);
                    }
                }
            }

            System.out.println("Unique words of length " + wordLength + " in interrogative sentences:");
            uniqueWords.forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Error during text processing: " + e.getMessage());
        }
    }

    private int indexOfOrLength(StringBuilder text, String delimiter, int startIndex) {
        int index = text.indexOf(delimiter, startIndex);
        return index == -1 ? text.length() : index;
    }
}
