package edu.hw1;

public class Task7 {
    private Task7() {}

    public static int rotateLeft(int n, int shift) {
        checkArgs(n, shift);

        int countBits = countBits(n);
        int tempShift = shift % countBits;
        int shiftedNum = n << tempShift;

        return (shiftedNum | (shiftedNum >> countBits)) & ~(Integer.MAX_VALUE << countBits);
    }

    public static int rotateRight(int n, int shift) {
        checkArgs(n, shift);

        int countBits = countBits(n);
        int tempShift = shift % countBits;

        return (n >> tempShift) | ((~(Integer.MAX_VALUE << tempShift) & n) << (countBits - tempShift));
    }

    private static int countBits(int n) {
        int count = 0;

        while (n >= Math.pow(2, count)) {
            count++;
        }

        return count;
    }

    private static void checkArgs(int n, int shift) {
        if (isNegative(n) && isNegative(shift)) {
            throw new IllegalArgumentException("First and second arguments must be more than 0");
        } else if (isNegative(n)) {
            throw new IllegalArgumentException("First argument must be more than 0");
        } else if (isNegative(shift)) {
            throw new IllegalArgumentException("Second argument must be more than 0");
        }
    }

    private static boolean isNegative(int n) {
        return n <= 0;
    }
}
