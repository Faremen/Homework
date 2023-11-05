package edu.hw3;

import java.util.HashMap;
import java.util.Objects;

public class Task1 {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final HashMap<Character, Character> ALPHABET_MAP = new HashMap<>();

    static {
        char[] charsAlphabet = ALPHABET.toCharArray();

        for (int i = 0; i < charsAlphabet.length; i++) {
            ALPHABET_MAP.put(
                charsAlphabet[i],
                charsAlphabet[charsAlphabet.length - 1 - i]
            );
        }
    }

    private Task1() {}

    public static String atbash(String str) {
        Objects.requireNonNull(str);

        StringBuilder stringBuilder = new StringBuilder();

        str.chars().forEach(intChar -> {
            char ch = (char) intChar;
            char upperCaseChar = Character.toUpperCase(ch);
            boolean isUpperCase = Character.isUpperCase(ch);

            if (ALPHABET_MAP.containsKey(upperCaseChar)) {
                char charFromMap = ALPHABET_MAP.get(upperCaseChar);

                if (isUpperCase) {
                    stringBuilder.append(Character.toUpperCase(charFromMap));
                } else {
                    stringBuilder.append(Character.toLowerCase(charFromMap));
                }
            } else {
                stringBuilder.append(ch);
            }
        });

        return stringBuilder.toString();
    }
}
