package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task1 {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    private static final String ONE_AND_MORE_WHITESPACE = "\\s+";

    private static final String SEPARATOR = " - ";

    private static final int SECONDS_PER_MINUTE = 60;

    private Task1() {}

    public static String calcAverageTime(String[] dates) {
        Objects.requireNonNull(dates);

        List<Duration> durations = getDurations(dates);

        long sumMinutes = 0;
        for (var dur : durations) {
            sumMinutes += dur.toMinutes();
        }

        Duration result = Duration.ofMinutes(sumMinutes / durations.size());

        return String.format("%dч %02dм", result.toHours(), result.toMinutes() % SECONDS_PER_MINUTE);
    }

    private static List<Duration> getDurations(String[] dates) {
        List<Duration> durations = new ArrayList<>();

        for (var str : dates) {
            String[] tempDates = getDates(str);

            LocalDateTime localDateTime1 = LocalDateTime.parse(tempDates[0], FORMATTER);
            LocalDateTime localDateTime2 = LocalDateTime.parse(tempDates[1], FORMATTER);

            if (localDateTime1.isBefore(localDateTime2)) {
                durations.add(Duration.between(localDateTime1, localDateTime2));
            } else {
                throw new IllegalArgumentException("first date after second date: " + str);
            }
        }

        return durations;
    }

    private static String[] getDates(String date) {
        return date.trim().replaceAll(ONE_AND_MORE_WHITESPACE, " ").split(SEPARATOR);
    }

}
