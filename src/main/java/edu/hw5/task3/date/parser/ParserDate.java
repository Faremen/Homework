package edu.hw5.task3.date.parser;

import java.time.LocalDate;

public abstract class ParserDate {
    private ParserDate next;

    public static ParserDate link(ParserDate first, ParserDate... chain) {
        ParserDate head = first;

        for (var elem : chain) {
            head.next = elem;
            head = elem;
        }

        return first;
    }

    public abstract LocalDate parse(String date);

    protected LocalDate parseNext(String date) {
        if (next == null) {
            return null;
        }

        return next.parse(date);
    }

}
