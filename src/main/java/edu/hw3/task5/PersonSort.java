package edu.hw3.task5;

import java.util.Comparator;


// Не смог решиться какой вариант лучше через 2 класса или через один, поэтому оставил оба варианта

public class PersonSort implements Comparator<Person> {

    private final boolean isASC;

    public PersonSort(boolean isASC) {
        this.isASC = isASC;
    }

    @Override
    public int compare(Person person1, Person person2) {
        int result;

        if (person1.surname() == null) {
            result = person2.surname() == null
                ? person1.name().compareTo(person2.name())
                : person1.name().compareTo(person2.surname());
        } else if (person2.surname() == null) {
            result = person1.surname().compareTo(person2.name());
        } else {
            result = person1.surname().compareTo(person2.surname());
        }

        return isASC ? result : -result;
    }
}