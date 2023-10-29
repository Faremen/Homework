package edu.hw3.task5;

import java.util.Comparator;


// Не смог решиться какой вариант лучше через 2 класса или через один, поэтому оставил оба варианта

public class PersonSortDESC implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        if (person1.surname() == null) {
            return person2.surname() == null
                ? -person1.name().compareTo(person2.name())
                : -person1.name().compareTo(person2.surname());
        } else if (person2.surname() == null) {
            return -person1.surname().compareTo(person2.name());
        } else {
            return -person1.surname().compareTo(person2.surname());
        }
    }
}
