package edu.hw5.task3.date.parser;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountDaysAgoParserDate extends ParserDate {

    private static final Pattern DAYS_AGO_PATTERN = Pattern.compile("^\\d+ days? ago$", Pattern.CASE_INSENSITIVE);

    @Override
    public LocalDate parse(String date) {
        Matcher matcher = DAYS_AGO_PATTERN.matcher(date);

        if (matcher.matches()) {
            String strDays = date.substring(0, date.indexOf(' '));

            int days;
            try {
                days = Integer.parseInt(strDays);
            } catch (NumberFormatException e) {
                return parseNext(date);
            }

            return LocalDate.now().minusDays(days);
        }


        return parseNext(date);
    }
}
