package edu.hw5;

import java.util.Objects;
import java.util.regex.Pattern;

public class Task6 {

    private Task6() {}

    public static boolean isContains(String str, String substring) {
        Objects.requireNonNull(str);
        Objects.requireNonNull(substring);

        Pattern pattern = Pattern.compile(Pattern.quote(substring));

        return pattern.matcher(str).find();
    }
}
