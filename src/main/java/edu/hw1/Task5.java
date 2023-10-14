package edu.hw1;

public class Task5 {

    private Task5() {}

    public static boolean isPalindromeDescendant(int number) {
        int num = Math.abs(number);

        if (isPalindrome(num)) {
            return true;
        }

        StringBuilder parentSB = new StringBuilder();
        StringBuilder childSB;
        parentSB.append(num);

        while (parentSB.length() % 2 == 0) {
            childSB = getChildSB(parentSB);

            if (isPalindrome(childSB.toString())) {
                return true;
            }

            parentSB = childSB;
        }

        return false;
    }

    private static StringBuilder getChildSB(StringBuilder parentSB) {
        StringBuilder childSB = new StringBuilder();

        for (int i = 0; i < parentSB.length(); i += 2) {
            int sum = parseCharToInt(parentSB.charAt(i)) + parseCharToInt(parentSB.charAt(i + 1));
            childSB.append(sum);
        }

        return childSB;
    }

    private static int parseCharToInt(char ch) {
        return Integer.parseInt(String.valueOf(ch));
    }

    private static boolean isPalindrome(String str) {
        return isPalindrome(str.toCharArray());
    }

    private static boolean isPalindrome(int num) {
        return isPalindrome(Integer.toString(num).toCharArray());
    }

    private static boolean isPalindrome(char[] charsNum) {
        if (charsNum.length == 1) {
            return false;
        }

        for (int i = 0; i < charsNum.length / 2; i++) {
            if (charsNum[i] != charsNum[charsNum.length - 1 - i]) {
                return false;
            }
        }

        return true;
    }
}
