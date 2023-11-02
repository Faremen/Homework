package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record ValidationError(String field, String message) {

    public static boolean hasError(Animal animal) {
        Objects.requireNonNull(animal);

        return animal.name() == null ||
            animal.type() == null ||
            animal.sex() == null ||
            animal.age() < 0 ||
            animal.heightInCm() <= 0 ||
            animal.weightInGram() <= 0;
    }

    public static List<ValidationError> getErrors(Animal animal) {
        Objects.requireNonNull(animal);

        List<ValidationError> result = new ArrayList<>();

        if (animal.name() == null) {
            result.add(new ValidationError("name", "name is null"));
        } if (animal.type() == null) {
            result.add(new ValidationError("type", "type is null"));
        } if (animal.sex() == null) {
            result.add(new ValidationError("sex", "sex is null"));
        } if (animal.age() < 0) {
            result.add(new ValidationError("age", "age < 0"));
        } if (animal.heightInCm() <= 0) {
            result.add(new ValidationError("heightInCm", "heightInCm <= 0"));
        } if (animal.weightInGram() <= 0) {
            result.add(new ValidationError("weightInGram", "weightInGram <= 0"));
        }

        return result;
    }
}
