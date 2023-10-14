package edu.hw1;

public class Task2 {
    private final static int TEN = 10;

    private Task2() {}

    public static int countDigits(int number) {
        int num = Math.abs(number);
        int count = 0;

        do {
            count++;
            num /= TEN;
        } while (num > 0);

        return count;
    }
}
