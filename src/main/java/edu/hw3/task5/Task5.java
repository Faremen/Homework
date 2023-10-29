package edu.hw3.task5;

import java.util.ArrayList;

public class Task5 {
    private static final String WHITE_SPASE_REGEX = "\\s+";

    private Task5() {}

    public static Person[] parseContacts(String[] strings, SortRule sortRule) {
        if (strings == null || sortRule == null) {
            return new Person[0];
        }

        ArrayList<Person> people = new ArrayList<>();

        for (var str : strings) {
            String[] tempStrArr = str.split(WHITE_SPASE_REGEX);

            if (tempStrArr.length >= 2) {
                people.add(new Person(tempStrArr[0], tempStrArr[1]));
            } else if (tempStrArr.length == 1) {
                people.add(new Person(tempStrArr[0], null));
            }
        }

        if (sortRule == SortRule.DESC) {
            people.sort(new PersonSort(false));
        } else {
            people.sort(new PersonSort(true));
        }

        return people.toArray(new Person[0]);
    }
}

