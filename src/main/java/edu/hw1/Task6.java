package edu.hw1;

import java.util.Arrays;

public class Task6 {
    private final static int TEN = 10;
    private final static int KAPREKAR_CONSTANT = 6174;
    private final static int MIN_NUMBER = 1000;
    private final static int MAX_NUMBER = 9999;
    private final static int[] NUMBERS_WITH_ALL_SAME_DIGITS = {1111, 2222, 3333, 4444, 5555, 6666, 7777, 8888, 9999};

    private Task6() {}

    public static int countK(int number) {
        checkNumber(number);

        if (number == KAPREKAR_CONSTANT) {
            return 0;
        }

        char[] charsNumber = Integer.toString(number).toCharArray();
        Arrays.sort(charsNumber);
        int ascending = Integer.parseInt(new String(charsNumber));

        char[] reversCharsNumber = reversChars(charsNumber);
        int descending = Integer.parseInt(new String(reversCharsNumber));

        int sub = descending - ascending;

        // Для случаев, когда получившееся вычитание стало меньше 1000
        // Такое возможно для чисел, у которых 3 цифры одинаковые, а одна оставшаяся цифра больше или меньше на 1
        // Например число 9989 => 9998 - 8999 = 999
        if (sub < MIN_NUMBER) {
            sub *= TEN;
        }

        return 1 + countK(sub);
    }

    private static char[] reversChars(char[] sortedChars) {
        char[] reversChars = new char[sortedChars.length];

        for (int i = 0; i < sortedChars.length; i++) {
            reversChars[i] = sortedChars[sortedChars.length - 1 - i];
        }

        return reversChars;
    }

    private static void checkNumber(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("Number must be greater than 999 and less than 9999");
        }

        for (int i : NUMBERS_WITH_ALL_SAME_DIGITS) {
            if (number == i) {
                throw new IllegalArgumentException("Four identical digits are not allowed");
            }
        }
    }
}
