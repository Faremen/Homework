package edu.hw5;

import java.util.Objects;
import java.util.regex.Pattern;

public class Task8 {

    private static final Pattern ODD_LENGTH = Pattern.compile("^([01]{2})*[01]$");

    private static final Pattern STARTS_ZERO_AND_ODD_LENGTH_OR_STARTS_ONE_AND_EVEN_LENGTH =
        Pattern.compile("(^0([01]{2})*$)|(^1([01]{2})*[01]$)");

    private static final Pattern COUNT_ZERO_MULTIPLE_THREE = Pattern.compile("^(1*01*01*01*)+$");

    private static final Pattern EXCEPT_11_OR_111 = Pattern.compile("^((?!^11$|^111$)[01])+$");

    private static final Pattern EACH_ODD_CHARACTER_IS_ONE = Pattern.compile("^1|((1[01])+1?)$");

    private static final Pattern NO_CONSECUTIVE_ONES = Pattern.compile("^((?!11)[01])+$");

    private Task8() {}

    public static boolean isOddLength(String str) {
        Objects.requireNonNull(str);

        return ODD_LENGTH.matcher(str).matches();
    }

    public static boolean isStartsZeroAndOddLengthOrStartsOneAndEvenLength(String str) {
        Objects.requireNonNull(str);

        return STARTS_ZERO_AND_ODD_LENGTH_OR_STARTS_ONE_AND_EVEN_LENGTH.matcher(str).matches();
    }

    public static boolean isCountZeroMultipleThree(String str) {
        Objects.requireNonNull(str);

        return COUNT_ZERO_MULTIPLE_THREE.matcher(str).matches();
    }

    public static boolean isExcept11Or111(String str) {
        Objects.requireNonNull(str);

        return EXCEPT_11_OR_111.matcher(str).matches();
    }

    public static boolean isEachOddCharacterIsOne(String str) {
        Objects.requireNonNull(str);

        return EACH_ODD_CHARACTER_IS_ONE.matcher(str).matches();
    }

    public static boolean isNoConsecutiveOnes(String str) {
        Objects.requireNonNull(str);

        return NO_CONSECUTIVE_ONES.matcher(str).matches();
    }
}
