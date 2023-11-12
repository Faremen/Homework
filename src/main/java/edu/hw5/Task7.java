package edu.hw5;

import java.util.Objects;
import java.util.regex.Pattern;

public class Task7 {

    private static final Pattern CONTAINS_LEAST_THREE_CHARACTERS_THIRD_CHARACTER_BEING_ZERO =
        Pattern.compile("^[01]{2}0[01]*$");

    private static final Pattern STARTS_AND_ENDS_SAME_CHARACTER = Pattern.compile("^([01])[01]*\\1$|^[01]$");

    private static final Pattern LENGTH_FROM_ONE_TO_THREE = Pattern.compile("^[01]{1,3}$");

    private Task7() {
    }

    public static boolean isContainsLeastThreeCharactersThirdCharacterBeingZero(String str) {
        Objects.requireNonNull(str);

        return CONTAINS_LEAST_THREE_CHARACTERS_THIRD_CHARACTER_BEING_ZERO.matcher(str).matches();
    }

    public static boolean isStartsAndEndsSameCharacter(String str) {
        Objects.requireNonNull(str);

        return STARTS_AND_ENDS_SAME_CHARACTER.matcher(str).matches();
    }

    public static boolean isLengthFromOneToThree(String str) {
        Objects.requireNonNull(str);

        return LENGTH_FROM_ONE_TO_THREE.matcher(str).matches();
    }
}
