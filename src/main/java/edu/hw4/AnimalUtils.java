package edu.hw4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("MultipleStringLiterals")
public class AnimalUtils {

    private AnimalUtils() {
    }

    // Задача 1
    // Отсортировать животных по росту от самого маленького к самому большому -> List<Animal>
    public static List<Animal> task1(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::heightInCm))
            .toList();
    }

    // Задача 2
    // Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых -> List<Animal>
    public static List<Animal> task2(List<Animal> animals, int k) {
        Objects.requireNonNull(animals);

        if (k <= 0) {
            throw new IllegalArgumentException("k <= 0");
        }

        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weightInGram).reversed())
            .limit(k)
            .toList();
    }

    // Задача 3
    // Сколько животных каждого вида -> Map<Animal.Type, Integer>
    public static Map<Animal.Type, Long> task3(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .collect(
                Collectors.groupingBy(
                    (a) -> Optional.ofNullable(a.type()),
                    Collectors.counting()
                )
            )
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    (e) -> e.getKey().orElse(null),
                    Map.Entry::getValue
                )
            );
    }

    // Задача 4
    // У какого животного самое длинное имя -> Animal
    public static Animal task4(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .max(Comparator.comparingInt((a) -> a.name() == null ? -1 : a.name().length()))
            .orElse(null);
    }

    // Задача 5
    // Каких животных больше: самцов или самок -> Sex
    public static Animal.Sex task5(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .filter((a) -> a.sex() != null)
            .collect(
                Collectors.groupingBy(
                    Animal::sex,
                    Collectors.counting()
                )
            )
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    // Задача 6
    // Самое тяжелое животное каждого вида -> Map<Animal.Type, Animal>
    public static Map<Animal.Type, Animal> task6(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .collect(Collectors.groupingBy((a) -> Optional.ofNullable(a.type())))
            .entrySet()
            .stream()
            .collect(HashMap::new, (m, v) ->
                m.put(
                    v.getKey().orElse(null),
                    v.getValue()
                        .stream()
                        .max(Comparator.comparingInt(Animal::weightInGram)).orElse(null)
                ), HashMap::putAll);
    }

    // Задача 7
    // K-е самое старое животное -> Animal
    public static Animal task7(List<Animal> animals, int k) {
        Objects.requireNonNull(animals);

        if (k <= 0) {
            throw new IllegalArgumentException("k <= 0");
        }

        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .toList()
            .get(k - 1);
    }

    // Задача 8
    // Самое тяжелое животное среди животных ниже k см -> Optional<Animal>
    public static Optional<Animal> task8(List<Animal> animals, int k) {
        Objects.requireNonNull(animals);

        if (k <= 0) {
            throw new IllegalArgumentException("k <= 0");
        }

        return animals.stream()
            .filter((a) -> a.heightInCm() < k)
            .max(Comparator.comparingInt(Animal::weightInGram));
    }

    // Задача 9
    // Сколько в сумме лап у животных в списке -> Integer
    public static Integer task9(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    // Задача 10
    // Список животных, возраст у которых не совпадает с количеством лап -> List<Animal>
    public static List<Animal> task10(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .filter((a) -> a.age() != a.paws())
            .toList();
    }

    // Задача 11
    // Список животных, которые могут укусить (bites == true) и рост которых превышает 100 см -> List<Animal>
    @SuppressWarnings("MagicNumber")
    public static List<Animal> task11(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .filter((a) -> a.bites() && a.heightInCm() > 100)
            .toList();
    }

    // Задача 12
    // Сколько в списке животных, вес которых превышает рост -> Integer
    public static Long task12(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .filter((a) -> a.weightInGram() > a.heightInCm())
            .count();
    }

    // Задача 13
    // Список животных, имена которых состоят из более чем двух слов -> List<Animal>
    public static List<Animal> task13(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .filter((a) -> {
                if (a.name() == null) {
                    return false;
                }

                return a.name()
                    .trim()
                    .replaceAll("\\s+", " ")
                    .split(" ")
                    .length > 2;
            })
            .toList();
    }

    // Задача 14
    // Есть ли в списке собака ростом более k см -> Boolean
    public static Boolean task14(List<Animal> animals, int k) {
        Objects.requireNonNull(animals);

        if (k < 0) {
            throw new IllegalArgumentException("k < 0");
        }

        return animals.stream()
            .anyMatch((a) -> {
                if (a.type() == null) {
                    return false;
                }

                return a.type() == Animal.Type.DOG && a.heightInCm() > k;
            });
    }

    // Задача 15
    // Найти суммарный вес животных каждого вида, которым от k до l лет -> Map<Animal.Type, Integer>
    public static Map<Animal.Type, Integer> task15(List<Animal> animals, int k, int l) {
        Objects.requireNonNull(animals);

        if (k < 0) {
            throw new IllegalArgumentException("k < 0");
        } else if (l < 0) {
            throw new IllegalArgumentException("l < 0");
        } else if (k >= l) {
            throw new IllegalArgumentException("l > k");
        }

        return animals.stream()
            .filter((a) -> a.age() > k && a.age() < l)
            .collect(
                Collectors.groupingBy(
                    (a) -> Optional.ofNullable(a.type()),
                    Collectors.summingInt(Animal::weightInGram)
                )
            ).entrySet()
            .stream()
            .collect(HashMap::new, (m, v) ->
                m.put(
                    v.getKey().orElse(null),
                    v.getValue()
                ), HashMap::putAll);
    }

    // Задача 16
    // Список животных, отсортированный по виду, затем по полу, затем по имени -> List<Animal>
    public static List<Animal> task16(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .sorted(Comparator
                .comparing(Animal::type, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(Animal::sex, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(Animal::name, Comparator.nullsLast(Comparator.naturalOrder())))
            .toList();
    }

    // Задача 17
    // Правда ли, что пауки кусаются чаще, чем собаки -> Boolean (если данных для ответа недостаточно, вернуть false)
    public static Boolean task17(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .filter((a) -> a.bites() && a.type() == Animal.Type.SPIDER)
            .count() > animals.stream()
            .filter((a) -> a.bites() && a.type() == Animal.Type.DOG)
            .count();
    }

    // Задача 18
    // Найти самую тяжелую рыбку в 2-х или более списках -> Animal
    @SafeVarargs
    public static Animal task18(List<Animal>... animals) {
        Arrays.stream(animals).forEach(Objects::requireNonNull);

        return Stream.of(animals)
            .flatMap(Collection::stream)
            .filter((a) -> a.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weightInGram))
            .orElse(null);
    }

    // Задача 19
    // Животные, в записях о которых есть ошибки: вернуть имя и список ошибок -> Map<String, Set<ValidationError>>.
    //
    // Класс ValidationError и набор потенциальных проверок нужно придумать самостоятельно.
    public static Map<String, Set<ValidationError>> task19(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .filter(ValidationError::hasError)
            .collect(Collectors.toMap(Animal::name, (a) -> new HashSet<>(ValidationError.getErrors(a))));
    }

    // Задача 20
    // Сделать результат предыдущего задания более читабельным:
    // вернуть имя и названия полей с ошибками, объединенные в строку -> Map<String, String>
    public static Map<String, String> task20(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .filter(ValidationError::hasError)
            .collect(Collectors.toMap(
                Animal::name,
                (a) -> ValidationError.getErrors(a).stream()
                    .map((e) -> String.format("Field: {%s} Error: {%s}", e.field(), e.message()))
                    .reduce("", (s1, s2) -> (s1 + s2 + "\n")).trim()
            ));
    }
}
