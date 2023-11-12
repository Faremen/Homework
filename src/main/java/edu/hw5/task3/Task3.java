package edu.hw5.task3;

import edu.hw5.task3.date.parser.CountDaysAgoParserDate;
import edu.hw5.task3.date.parser.DashParserDate;
import edu.hw5.task3.date.parser.ParserDate;
import edu.hw5.task3.date.parser.SlashParserDate;
import edu.hw5.task3.date.parser.TodayParserDate;
import edu.hw5.task3.date.parser.TomorrowParserDate;
import edu.hw5.task3.date.parser.YesterdayParserDate;
import java.time.LocalDate;
import java.util.Optional;

public class Task3 {

    private static final String ONE_AND_MORE_WHITESPACE = "\\s+";

    private static final ParserDate PARSER_DATE = ParserDate.link(
        new DashParserDate(),
        new SlashParserDate(),
        new TomorrowParserDate(),
        new TodayParserDate(),
        new YesterdayParserDate(),
        new CountDaysAgoParserDate()
    );

    private Task3() {}

    public static Optional<LocalDate> parseDate(String date) {
        if (date == null) {
            return Optional.empty();
        }

        LocalDate result = PARSER_DATE.parse(date.trim().replaceAll(ONE_AND_MORE_WHITESPACE, " "));

        return Optional.ofNullable(result);
    }
}
