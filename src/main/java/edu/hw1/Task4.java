package edu.hw1;

public class Task4 {

    private Task4() {}

    public static String fixString(String str) {
        if (str.length() == 0) {
            throw new IllegalArgumentException("Empty string");
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < str.length(); i += 2) {
            if (i + 1 <= str.length() - 1) {
                stringBuilder.append(str.charAt(i + 1)).append(str.charAt(i));
            } else {
                stringBuilder.append(str.charAt(i));
            }
        }

        return stringBuilder.toString();
    }
}
