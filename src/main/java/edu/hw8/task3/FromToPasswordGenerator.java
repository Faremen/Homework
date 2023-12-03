package edu.hw8.task3;

import java.util.NoSuchElementException;

public final class FromToPasswordGenerator {
    private final String alphabet;
    private final int[] indices;
    private final int to;

    FromToPasswordGenerator(String alphabet, int from, int to, int passwordLength) {
        this.alphabet = alphabet;
        this.indices = new int[passwordLength];
        this.indices[0] = from;
        this.to = to;
    }

    public String generate() {
        if (!hasNextPassword()) {
            throw new NoSuchElementException("No more passwords available.");
        }

        var password = new StringBuilder();
        for (int index : indices) {
            password.append(alphabet.charAt(index));
        }

        generateNextPassword();
        return password.toString();
    }

    public boolean hasNextPassword() {
        return indices[0] != to + 1;
    }

    private void generateNextPassword() {
        for (int i = indices.length - 1; i >= 0; i--) {
            indices[i]++;

            if (indices[i] == alphabet.length()) {
                if (i == 0) {
                    indices[i] = alphabet.length();
                } else {
                    indices[i] = 0;
                }
            } else {
                break;
            }
        }
    }
}
