package edu.hw1;

public class Task4 {

    private Task4() {}

    public static String fixString(String str) {
        if (str.length() == 0) {
            throw new IllegalArgumentException("Empty string");
        } else if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Length string must be even");
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < str.length(); i += 2) {
            stringBuilder.append(str.charAt(i + 1)).append(str.charAt(i));
        }

        return stringBuilder.toString();
    }
}
