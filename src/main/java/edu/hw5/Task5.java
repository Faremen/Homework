package edu.hw5;

import java.util.Objects;
import java.util.regex.Pattern;

public class Task5 {

    public static final Pattern RUS_CAR_NUMBER_PATTERN =
        Pattern.compile("^[АВЕКМНОРСТУХ](\\d{3})([АВЕКМНОРСТУХ]{2})(\\d){1,3}$");

    private Task5() {
    }

    public static boolean checkRusCarNumber(String number) {
        Objects.requireNonNull(number);

        return RUS_CAR_NUMBER_PATTERN.matcher(number).matches();
    }
}
