package edu.hw5.task3.date.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SlashParserDate extends ParserDate {

    private final DateTimeFormatter formatterFourDigitYear = DateTimeFormatter.ofPattern("d/M/yyyy");
    private final DateTimeFormatter formatterTwoDigitYear = DateTimeFormatter.ofPattern("d/M/yy");

    @Override
    public LocalDate parse(String date) {
        try {
            return LocalDate.parse(date, formatterFourDigitYear);
        } catch (DateTimeParseException firstE) {
            try {
                return LocalDate.parse(date, formatterTwoDigitYear);
            } catch (DateTimeParseException secondE) {
                return parseNext(date);
            }
        }
    }
}
