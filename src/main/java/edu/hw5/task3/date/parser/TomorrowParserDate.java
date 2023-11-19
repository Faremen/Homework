package edu.hw5.task3.date.parser;

import java.time.LocalDate;

public class TomorrowParserDate extends ParserDate {

    private static final String TOMORROW = "tomorrow";

    @Override
    public LocalDate parse(String date) {
        if (date.equalsIgnoreCase(TOMORROW)) {
            return LocalDate.now().plusDays(1);
        }

        return parseNext(date);
    }
}
