package edu.hw5;

import java.util.Objects;
import java.util.regex.Pattern;

public class Task4 {

    private static final Pattern REQUIRED_CHARS_IN_PASSWORD = Pattern.compile("[~!@#$%^&*|]");

    private Task4() {}

    public static boolean checkPassword(String password) {
        Objects.requireNonNull(password);

        return REQUIRED_CHARS_IN_PASSWORD.matcher(password).find();
    }
}
