package edu.hw3;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Task2 {

    private Task2() {}

    public static String[] clusterize(String str) {
        Objects.requireNonNull(str);

        Deque<Character> deque = new LinkedList<>();
        List<String> result = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                deque.add(ch);
                stringBuilder.append(ch);
            } else if (ch == ')' && !deque.isEmpty()) {
                deque.pop();
                stringBuilder.append(ch);

                if (deque.isEmpty()) {
                    result.add(stringBuilder.toString());
                    stringBuilder.setLength(0);
                }
            } else {
                return null;
            }
        }

        if (!deque.isEmpty()) {
            return null;
        }

        return result.isEmpty() ? null : result.toArray(new String[0]);
    }
}
