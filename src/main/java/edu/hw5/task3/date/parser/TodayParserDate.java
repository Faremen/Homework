package edu.hw5.task3.date.parser;

import java.time.LocalDate;

public class TodayParserDate extends ParserDate {

    private static final String TODAY = "today";

    @Override
    public LocalDate parse(String date) {
        if (date.equalsIgnoreCase(TODAY)) {
            return LocalDate.now();
        }

        return parseNext(date);
    }
}
