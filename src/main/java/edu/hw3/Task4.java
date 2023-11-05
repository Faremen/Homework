package edu.hw3;

import java.util.NavigableMap;
import java.util.TreeMap;

@SuppressWarnings(value = "MagicNumber")
public class Task4 {

    private static final int UPPER_BOUND = 3999;
    private static final int LOWER_BOUND = 1;

    private static final NavigableMap<Integer, String> ARABIC_ROMAN_NUMBERS = new TreeMap<>();

    static {
        ARABIC_ROMAN_NUMBERS.put(1000, "M");
        ARABIC_ROMAN_NUMBERS.put(900, "CM");
        ARABIC_ROMAN_NUMBERS.put(500, "D");
        ARABIC_ROMAN_NUMBERS.put(400, "CD");
        ARABIC_ROMAN_NUMBERS.put(100, "C");
        ARABIC_ROMAN_NUMBERS.put(90, "XC");
        ARABIC_ROMAN_NUMBERS.put(50, "L");
        ARABIC_ROMAN_NUMBERS.put(40, "XL");
        ARABIC_ROMAN_NUMBERS.put(10, "X");
        ARABIC_ROMAN_NUMBERS.put(9, "IX");
        ARABIC_ROMAN_NUMBERS.put(5, "V");
        ARABIC_ROMAN_NUMBERS.put(4, "IV");
        ARABIC_ROMAN_NUMBERS.put(1, "I");
    }

    private Task4() {}

    public static String convertToRoman(int num) {
        if (num > UPPER_BOUND || num < LOWER_BOUND) {
            throw new IllegalArgumentException("number must be within the boundaries "
                + "[" + LOWER_BOUND + ", " + UPPER_BOUND + "]");
        }

        int tempNum = num;
        StringBuilder result = new StringBuilder();

        for (var key : ARABIC_ROMAN_NUMBERS.descendingKeySet()) {
            while (tempNum >= key) {
                tempNum -= key;
                result.append(ARABIC_ROMAN_NUMBERS.get(key));
            }
        }

        return result.toString();
    }
}
