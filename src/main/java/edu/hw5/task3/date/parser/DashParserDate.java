package edu.hw5.task3.date.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DashParserDate extends ParserDate {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");

    @Override
    public LocalDate parse(String date) {
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return parseNext(date);
        }
    }
}
