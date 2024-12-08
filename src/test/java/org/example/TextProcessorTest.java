package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextProcessorTest {
    private TextProcessor textProcessor;

    @BeforeEach
    void setUp() {
        textProcessor = new TextProcessor();
    }

    @Test
    void splitText_SimpleDelimiter() {
        StringBuilder text = new StringBuilder("Hello,World,Test");
        List<StringBuilder> result = textProcessor.splitText(text, ",");

        assertEquals(3, result.size());
        assertEquals("Hello", result.get(0).toString());
        assertEquals("World", result.get(1).toString());
        assertEquals("Test", result.get(2).toString());
    }

    @Test
    void splitText_EmptyText() {
        StringBuilder text = new StringBuilder("");
        List<StringBuilder> result = textProcessor.splitText(text, ",");

        assertTrue(result.isEmpty());
    }

    @Test
    void extractUniqueWordsFromQuestions_ValidInput() {
        StringBuilder text = new StringBuilder("Who are you? What is your name? Hello there.");
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        textProcessor.extractUniqueWordsFromQuestions(text, 3);

        String output = outContent.toString();
        assertTrue(output.contains("are"));
        assertTrue(output.contains("Who"));
        assertTrue(output.contains("you"));
        assertFalse(output.contains("Hello"));
    }

    @Test
    void extractUniqueWordsFromQuestions_MixedPunctuation() {
        StringBuilder text = new StringBuilder("Who are you? This is great! What now? The end.");
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        textProcessor.extractUniqueWordsFromQuestions(text, 3);

        String output = outContent.toString();
        assertTrue(output.contains("Who"));
        assertTrue(output.contains("are"));
        assertTrue(output.contains("you"));
        assertTrue(output.contains("now"));
        assertFalse(output.contains("end"));
    }

    @Test
    void extractUniqueWordsFromQuestions_NoQuestions() {
        StringBuilder text = new StringBuilder("This is a statement. Another statement!");
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        textProcessor.extractUniqueWordsFromQuestions(text, 3);

        String output = outContent.toString();
        assertTrue(output.contains("Unique words of length"));
        assertFalse(output.contains("the"));
    }

    @Test
    void extractUniqueWordsFromQuestions_InvalidWords() {
        StringBuilder text = new StringBuilder("Who123 are you? What is 456?");
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        textProcessor.extractUniqueWordsFromQuestions(text, 3);

        String output = outContent.toString();
        assertTrue(output.contains("are"));
        assertTrue(output.contains("you"));
        assertFalse(output.contains("456"));
        assertFalse(output.contains("Who123"));
    }

    @Test
    void extractUniqueWordsFromQuestions_EmptyInput() {
        StringBuilder text = new StringBuilder("");
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        textProcessor.extractUniqueWordsFromQuestions(text, 3);

        String output = outContent.toString();
        assertTrue(output.contains("Unique words of length"));
    }

    @Test
    void extractUniqueWordsFromQuestions_NullInput() {
        java.io.ByteArrayOutputStream errContent = new java.io.ByteArrayOutputStream();
        System.setErr(new java.io.PrintStream(errContent));

        textProcessor.extractUniqueWordsFromQuestions(null, 3);

        String error = errContent.toString();
        assertTrue(error.contains("Error during text processing"));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }
}
