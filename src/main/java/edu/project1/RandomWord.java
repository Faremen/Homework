package edu.project1;

import java.util.Random;

public class RandomWord {
    private final static String[] WORDS = {"Тинькофф", "Слово", "Предположение", "Выход"};
    private final static Random RANDOM = new Random();

    private RandomWord() {}

    public static String getRandomWord() {
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }
}
