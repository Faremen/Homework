package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private static final int THIRTEEN = 13;

    private Task2() {}

    public static List<LocalDate> findAllFridaysThirteenthInYear(int year) {
        if (year < 0) {
            throw new IllegalArgumentException("year < 0");
        }

        List<LocalDate> fridaysThirteenth = new ArrayList<>();

        for (var mouth : Month.values()) {
            LocalDate localDate = LocalDate.of(year, mouth, THIRTEEN);

            if (localDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridaysThirteenth.add(localDate);
            }
        }

        return fridaysThirteenth;
    }

    @SuppressWarnings("MagicNumber")
    public static LocalDate findNextFridayThirteenth(LocalDate date) {
        return date
            .with(temporal -> {
                Temporal result = temporal.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

                while (result.get(ChronoField.DAY_OF_MONTH) != THIRTEEN) {
                    result = result.plus(7, ChronoUnit.DAYS);
                }

                return result;
            });
    }
}
