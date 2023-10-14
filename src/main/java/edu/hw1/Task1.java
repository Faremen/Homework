package edu.hw1;

public class Task1 {
    private final static int SECONDS_IN_MINUTE = 60;
    private final static String SEPARATOR = ":";

    private Task1() {}

    public static int minutesToSeconds(String str) {
        if (str == null) {
            return -1;
        }

        String[] strings = str.split(SEPARATOR);

        try {
            if (strings.length == 2) {
                int minutes = Integer.parseUnsignedInt(strings[0]);
                int seconds = Integer.parseUnsignedInt(strings[1]);

                if (seconds < SECONDS_IN_MINUTE && seconds >= 0) {
                    return minutes * SECONDS_IN_MINUTE + seconds;
                }
            }
        } catch (NumberFormatException ignored) {

        }

        return -1;
    }
}
