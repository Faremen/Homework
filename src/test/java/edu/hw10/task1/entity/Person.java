package edu.hw10.task1.entity;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

import java.util.Objects;

public final class Person {
    private final String name;
    private final int age;

    public Person(@NotNull @Max(40) String name, @Min(0) @Max(100) int age) {
        this.name = name;
        this.age = age;
    }

    public static Person create(@NotNull @Max(40) String name, @Min(0) @Max(100) int age) {
        return new Person(name, age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        var that = (Person) obj;
        return Objects.equals(this.name, that.name) &&
                this.age == that.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
