package edu.hw3.task5;

import java.util.Comparator;

public class PersonSort implements Comparator<Person> {

    private final boolean isASC;

    public PersonSort(boolean isASC) {
        this.isASC = isASC;
    }

    @Override
    public int compare(Person person1, Person person2) {
        int result;

        if (person1.surname() == null && person2.surname() == null) {
            result = person1.name().compareTo(person2.name());
        } else if (person1.surname() == null) {
            result = person1.name().compareTo(person2.surname());
        } else if (person2.surname() == null) {
            result = person1.surname().compareTo(person2.name());
        } else {
            result = person1.surname().compareTo(person2.surname());
        }

        return isASC ? result : -result;
    }
}
