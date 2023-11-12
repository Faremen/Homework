package edu.hw5.task3.date.parser;

import java.time.LocalDate;

public class YesterdayParserDate extends ParserDate {

    private static final String YESTERDAY = "yesterday";

    @Override
    public LocalDate parse(String date) {
        if (date.equalsIgnoreCase(YESTERDAY)) {
            return LocalDate.now().minusDays(1);
        }

        return parseNext(date);
    }
}
