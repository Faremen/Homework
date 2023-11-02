package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AnimalUtilsTest {

    private List<Animal> inputAnimals = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        inputAnimals.add(new Animal("Maxwell",      Animal.Type.CAT,     Animal.Sex.M, 3,     20,    4_000,    true));
        inputAnimals.add(new Animal("  Samoed ",    Animal.Type.DOG,     Animal.Sex.F, 15,    60,    15_000,   true));
        inputAnimals.add(new Animal("Hummingbird",  Animal.Type.BIRD,    Animal.Sex.M, 1,     10,    5,        false));
        inputAnimals.add(new Animal("Pavuk",        Animal.Type.SPIDER,  Animal.Sex.M, 0,     2,     1,        false));
        inputAnimals.add(new Animal("Shark",        Animal.Type.FISH,    Animal.Sex.F, 6,     200,   111_000,  true));
        inputAnimals.add(new Animal("Bubble fish",  Animal.Type.FISH,    Animal.Sex.F, 6,     150,   1_000,    false));
        inputAnimals.add(new Animal("  ULTRA   MEGA     PAVUK  ",   Animal.Type.SPIDER,  Animal.Sex.F, 666,   300,   143_500,  true));
        inputAnimals.add(new Animal("Small   Cat",  Animal.Type.CAT,     Animal.Sex.M, 0,     2,     40,       false));
        inputAnimals.add(new Animal("Hleb",         Animal.Type.DOG,     Animal.Sex.M, 4,     30,    9_000,    false));
        inputAnimals.add(new Animal("UFO",          Animal.Type.BIRD,    null,  -9999, -9999, -9999,    false));
        inputAnimals.add(new Animal(null,           null,         null,  0,     0,     0,        false));
        inputAnimals.add(new Animal("MinDog",       Animal.Type.DOG,     Animal.Sex.M, 0,     0,     0,        false));
    }

    // Отсортировать животных по росту от самого маленького к самому большому -> List<Animal>
    @Test
    public void test1_InputAnimalList_ResultSortedAnimalListByHeight() {
        // Given
        List<Animal> expected = new ArrayList<>();
        expected.add(new Animal("UFO",          Animal.Type.BIRD,    null,  -9999, -9999, -9999,    false));
        expected.add(new Animal(null,           null,         null,  0,     0,     0,        false));
        expected.add(new Animal("MinDog",       Animal.Type.DOG,     Animal.Sex.M, 0,     0,     0,        false));
        expected.add(new Animal("Pavuk",        Animal.Type.SPIDER,  Animal.Sex.M, 0,     2,     1,        false));
        expected.add(new Animal("Small   Cat",     Animal.Type.CAT,     Animal.Sex.M, 0,     2,     40,       false));
        expected.add(new Animal("Hummingbird",  Animal.Type.BIRD,    Animal.Sex.M, 1,     10,    5,        false));
        expected.add(new Animal("Maxwell",      Animal.Type.CAT,     Animal.Sex.M, 3,     20,    4_000,    true));
        expected.add(new Animal("Hleb",         Animal.Type.DOG,     Animal.Sex.M, 4,     30,    9_000,    false));
        expected.add(new Animal("  Samoed ",    Animal.Type.DOG,     Animal.Sex.F, 15,    60,    15_000,   true));
        expected.add(new Animal("Bubble fish",  Animal.Type.FISH,    Animal.Sex.F, 6,     150,   1_000,    false));
        expected.add(new Animal("Shark",        Animal.Type.FISH,    Animal.Sex.F, 6,     200,   111_000,  true));
        expected.add(new Animal("  ULTRA   MEGA     PAVUK  ",   Animal.Type.SPIDER,  Animal.Sex.F, 666,   300,   143_500,  true));

        // When
        var actual = AnimalUtils.task1(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test1_InputEmptyAnimalList_ResultEmptyAnimalList() {
        // Given
        List<Animal> inputAnimals = new ArrayList<>();
        List<Animal> expected = new ArrayList<>();

        // When
        var actual = AnimalUtils.task1(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    // Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых -> List<Animal>
    @Test
    public void test2_InputEmptyAnimalList_ResultSortedAnimalListByWeightReversed() {
        // Given
        int k = 4;
        List<Animal> expected = new ArrayList<>();
        expected.add(new Animal("  ULTRA   MEGA     PAVUK  ",   Animal.Type.SPIDER,  Animal.Sex.F, 666,   300,   143_500,  true));
        expected.add(new Animal("Shark",        Animal.Type.FISH,    Animal.Sex.F, 6,     200,   111_000,  true));
        expected.add(new Animal("  Samoed ",    Animal.Type.DOG,     Animal.Sex.F, 15,    60,    15_000,   true));
        expected.add(new Animal("Hleb",         Animal.Type.DOG,     Animal.Sex.M, 4,     30,    9_000,    false));

        // When
        var actual = AnimalUtils.task2(inputAnimals, k);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test2_InputEmptyAnimalList_ResultEmptyAnimalList() {
        // Given
        int k = 4;
        List<Animal> inputAnimals = new ArrayList<>();
        List<Animal> expected = new ArrayList<>();

        // When
        var actual = AnimalUtils.task2(inputAnimals, k);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "0",
        "-3",
        "" + Integer.MIN_VALUE
    })
    public void test2_InputAnimalListAndKLessOrEqualThanZero_ResultIllegalArgumentException(int k) {
        assertThatThrownBy(() -> AnimalUtils.task2(inputAnimals, k))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("k <= 0");
    }

    // Сколько животных каждого вида -> Map<Animal.Type, Integer>
    @Test
    public void test3_InputAnimalList_ResultMapAnimalTypeCount() {
        // Given
        Map<Animal.Type, Long> expected = new HashMap<>();
        expected.put(Animal.Type.CAT, 2L);
        expected.put(Animal.Type.DOG, 3L);
        expected.put(Animal.Type.BIRD, 2L);
        expected.put(Animal.Type.SPIDER, 2L);
        expected.put(Animal.Type.FISH, 2L);
        expected.put(null, 1L);

        // When
        var actual = AnimalUtils.task3(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test3_InputEmptyAnimalList_ResultEmptyMap() {
        // Given
        List<Animal> inputAnimals = new ArrayList<>();
        Map<Animal.Type, Long> expected = new HashMap<>();

        // When
        var actual = AnimalUtils.task3(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    // У какого животного самое длинное имя -> Animal
    @Test
    public void test4_InputAnimalList_ResultAnimalWithLongestName() {
        // Given
        Animal expected = new Animal("  ULTRA   MEGA     PAVUK  ", Animal.Type.SPIDER, Animal.Sex.F, 666, 300, 143_500, true);

        // When
        var actual = AnimalUtils.task4(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test4_InputEmptyAnimalList_ResultNull() {
        // Given
        List<Animal> inputAnimals = new ArrayList<>();
        Animal expected = null;

        // When
        var actual = AnimalUtils.task4(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    // Каких животных больше: самцов или самок -> Sex
    @Test
    public void test5_InputAnimalList_ResultMostCommonSex() {
        // Given
        Animal.Sex expected = Animal.Sex.M;

        // When
        var actual = AnimalUtils.task5(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test5_InputEmptyAnimalList_ResultNull() {
        // Given
        List<Animal> inputAnimals = new ArrayList<>();
        Animal.Sex expected = null;

        // When
        var actual = AnimalUtils.task5(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    // Самое тяжелое животное каждого вида -> Map<Animal.Type, Animal>
    @Test
    public void test6_InputAnimalList_ResultMapHeaviestAnimalEachTypes() {
        // Given
        Map<Animal.Type, Animal> expected = new HashMap<>();
        expected.put(Animal.Type.CAT,    new Animal("Maxwell",     Animal.Type.CAT,    Animal.Sex.M, 3,     20,     4_000,   true));
        expected.put(Animal.Type.DOG,    new Animal("  Samoed ",   Animal.Type.DOG,    Animal.Sex.F, 15,    60,     15_000,  true));
        expected.put(Animal.Type.BIRD,   new Animal("Hummingbird", Animal.Type.BIRD,   Animal.Sex.M, 1,     10,     5,       false));
        expected.put(Animal.Type.SPIDER, new Animal("  ULTRA   MEGA     PAVUK  ",  Animal.Type.SPIDER, Animal.Sex.F, 666,   300,    143_500, true));
        expected.put(Animal.Type.FISH,   new Animal("Shark",       Animal.Type.FISH,   Animal.Sex.F, 6,     200,    111_000, true));
        expected.put(null,               new Animal(null,           null,         null,  0,     0,     0,        false));

        // When
        var actual = AnimalUtils.task6(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test6_InputEmptyAnimalList_ResultEmptyMap() {
        // Given
        List<Animal> inputAnimals = new ArrayList<>();
        Map<Animal.Type, Animal> expected = new HashMap<>();

        // When
        var actual = AnimalUtils.task6(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    // K-е самое старое животное -> Animal
    @Test
    public void test7_InputAnimalListAndK_ResultKOldestAnimal() {
        // Given
        int k = 4;
        Animal expected = new Animal("Bubble fish", Animal.Type.FISH, Animal.Sex.F, 6, 150, 1_000, false);

        // When
        var actual = AnimalUtils.task7(inputAnimals, k);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "0",
        "-3",
        "" + Integer.MIN_VALUE
    })
    public void test7_InputAnimalListAndKLessOrEqualThanZero_ResultIllegalArgumentException(int k) {
        assertThatThrownBy(() -> AnimalUtils.task7(inputAnimals, k))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("k <= 0");
    }

    // Самое тяжелое животное среди животных ниже k см -> Optional<Animal>
    @Test
    public void test8_InputAnimalListAndK_ResultHeaviestAnimalAmongAnimalsBelowKCm() {
        // Given
        int k = 15;
        Animal expected = new Animal("Small   Cat", Animal.Type.CAT, Animal.Sex.M, 0, 2, 40, false);

        // When
        var actual = AnimalUtils.task8(inputAnimals, k).orElse(null);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "0",
        "-3",
        "" + Integer.MIN_VALUE
    })
    public void test8_InputAnimalListAndKLessOrEqualThanZero_ResultIllegalArgumentException(int k) {
        assertThatThrownBy(() -> AnimalUtils.task8(inputAnimals, k))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("k <= 0");
    }

    // Сколько в сумме лап у животных в списке -> Integer
    @Test
    public void test9_InputAnimalList_ResultCountPaws() {
        // Given
        int expected = 40;

        // When
        var actual = AnimalUtils.task9(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test9_InputEmptyAnimalList_ResultZeroCountPaws() {
        // Given
        List<Animal> inputAnimals = new ArrayList<>();
        int expected = 0;

        // When
        var actual = AnimalUtils.task9(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    // Список животных, возраст у которых не совпадает с количеством лап -> List<Animal>
    @Test
    public void test10_InputAnimalList_ResultListAnimalsAgeWhichDoesNotMatchNumberPaws() {
        // Given
        List<Animal> expected = new ArrayList<>();

        expected.add(new Animal("Maxwell",      Animal.Type.CAT,     Animal.Sex.M, 3,     20,    4_000,    true));
        expected.add(new Animal("  Samoed ",    Animal.Type.DOG,     Animal.Sex.F, 15,    60,    15_000,   true));
        expected.add(new Animal("Hummingbird",  Animal.Type.BIRD,    Animal.Sex.M, 1,     10,    5,        false));
        expected.add(new Animal("Pavuk",        Animal.Type.SPIDER,  Animal.Sex.M, 0,     2,     1,        false));
        expected.add(new Animal("Shark",        Animal.Type.FISH,    Animal.Sex.F, 6,     200,   111_000,  true));
        expected.add(new Animal("Bubble fish",  Animal.Type.FISH,    Animal.Sex.F, 6,     150,   1_000,    false));
        expected.add(new Animal("  ULTRA   MEGA     PAVUK  ",   Animal.Type.SPIDER,  Animal.Sex.F, 666,   300,   143_500,  true));
        expected.add(new Animal("Small   Cat",     Animal.Type.CAT,     Animal.Sex.M, 0,     2,     40,       false));
        expected.add(new Animal("UFO",          Animal.Type.BIRD,    null,  -9999, -9999, -9999,    false));
        expected.add(new Animal("MinDog",       Animal.Type.DOG,     Animal.Sex.M, 0,     0,     0,        false));

        // When
        var actual = AnimalUtils.task10(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test10_InputEmptyAnimalList_ResultEmptyAnimalList() {
        // Given
        List<Animal> inputAnimals = new ArrayList<>();
        List<Animal> expected = new ArrayList<>();

        // When
        var actual = AnimalUtils.task10(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    // Список животных, которые могут укусить (bites == true) и рост которых превышает 100 см -> List<Animal>
    @Test
    public void test11_InputAnimalList_ResultListAnimalsWhichCanBiteAndHeightMoreThan100Cm() {
        // Given
        List<Animal> expected = new ArrayList<>();

        expected.add(new Animal("Shark",        Animal.Type.FISH,    Animal.Sex.F, 6,     200,   111_000,  true));
        expected.add(new Animal("  ULTRA   MEGA     PAVUK  ",   Animal.Type.SPIDER,  Animal.Sex.F, 666,   300,   143_500,  true));

        // When
        var actual = AnimalUtils.task11(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test11_InputEmptyAnimalList_ResultEmptyListAnimal() {
        // Given
        List<Animal> inputAnimals = new ArrayList<>();
        List<Animal> expected = new ArrayList<>();

        // When
        var actual = AnimalUtils.task11(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    // Сколько в списке животных, вес которых превышает рост -> Integer
    @Test
    public void test12_InputAnimalList_ResultCountAnimalsWhoseWeightMoreThanHeight() {
        // Given
        Long expected = 7L;

        // When
        var actual = AnimalUtils.task12(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test12_InputEmptyAnimalList_ResultZeroCount() {
        // Given
        List<Animal> inputAnimals = new ArrayList<>();
        Long expected = 0L;

        // When
        var actual = AnimalUtils.task12(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    // Список животных, имена которых состоят из более чем двух слов -> List<Animal>
    @Test
    public void test13_InputAnimalList_ResultListAnimalsWhoseNamesConsistMoreThanTwoWords() {
        // Given
        List<Animal> expected = new ArrayList<>();

        expected.add(new Animal("  ULTRA   MEGA     PAVUK  ", Animal.Type.SPIDER, Animal.Sex.F, 666, 300, 143_500,  true));

        // When
        var actual = AnimalUtils.task13(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test13_InputEmptyAnimalList_ResultEmptyListAnimal() {
        // Given
        List<Animal> inputAnimals = new ArrayList<>();
        List<Animal> expected = new ArrayList<>();

        // When
        var actual = AnimalUtils.task13(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    // Есть ли в списке собака ростом более k см -> Boolean
    @ParameterizedTest
    @CsvSource(value = {
        "10,  true",
        "100, false"
    })
    public void test14_InputAnimalList_ResultIsContainDogWithHeightGreaterThanK(int k, boolean expected) {
        // When
        var actual = AnimalUtils.task14(inputAnimals, k);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test14_InputEmptyAnimalList_ResultFalse() {
        // Given
        int k = 0;
        List<Animal> inputAnimals = new ArrayList<>();
        boolean expected = false;

        // When
        var actual = AnimalUtils.task14(inputAnimals, k);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "-3",
        "" + Integer.MIN_VALUE
    })
    public void test14_InputAnimalListAndKLessOrEqualThanZero_ResultIllegalArgumentException(int k) {
        assertThatThrownBy(() -> AnimalUtils.task14(inputAnimals, k))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("k < 0");
    }

    // Найти суммарный вес животных каждого вида, которым от k до l лет -> Map<Animal.Type, Integer>
    @Test
    public void test15_InputAnimalListAndKAndL_ResultMapAnimalTypeSumWeight() {
        // Given
        int k = 5;
        int l = 10;

        Map<Animal.Type, Integer> expected = new HashMap<>();
        expected.put(Animal.Type.FISH, 112_000);

        // When
        var actual = AnimalUtils.task15(inputAnimals, k, l);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test15_InputEmptyAnimalListAndKAndL_ResultEmptyMap() {
        // Given
        int k = 5;
        int l = 10;
        List<Animal> inputAnimals = new ArrayList<>();
        Map<Animal.Type, Integer> expected = new HashMap<>();

        // When
        var actual = AnimalUtils.task15(inputAnimals, k, l);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "-3, -4, k < 0",
        "3, -4, l < 0",
        "4, 2, l > k"
    })
    public void test15_InputAnimalListAndKAndLLessOrEqualThanZero_ResultIllegalArgumentException(int k, int l, String message) {
        assertThatThrownBy(() -> AnimalUtils.task15(inputAnimals, k, l))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(message);
    }


    // Список животных, отсортированный по виду, затем по полу, затем по имени -> List<Animal>
    @Test
    public void test16_InputAnimalList_ResultSortedByAnimalTypeBySexByNameAnimalList() {
        // Given
        List<Animal> expected  = new ArrayList<>();
        expected.add(new Animal("Maxwell",      Animal.Type.CAT,     Animal.Sex.M, 3,     20,    4_000,    true));
        expected.add(new Animal("Small   Cat",  Animal.Type.CAT,     Animal.Sex.M, 0,     2,     40,       false));
        expected.add(new Animal("Hleb",         Animal.Type.DOG,     Animal.Sex.M, 4,     30,    9_000,    false));
        expected.add(new Animal("MinDog",       Animal.Type.DOG,     Animal.Sex.M, 0,     0,     0,        false));
        expected.add(new Animal("  Samoed ",    Animal.Type.DOG,     Animal.Sex.F, 15,    60,    15_000,   true));
        expected.add(new Animal("Hummingbird",  Animal.Type.BIRD,    Animal.Sex.M, 1,     10,    5,        false));
        expected.add(new Animal("UFO",          Animal.Type.BIRD,    null,  -9999, -9999, -9999,    false));
        expected.add(new Animal("Bubble fish",  Animal.Type.FISH,    Animal.Sex.F, 6,     150,   1_000,    false));
        expected.add(new Animal("Shark",        Animal.Type.FISH,    Animal.Sex.F, 6,     200,   111_000,  true));
        expected.add(new Animal("Pavuk",        Animal.Type.SPIDER,  Animal.Sex.M, 0,     2,     1,        false));
        expected.add(new Animal("  ULTRA   MEGA     PAVUK  ",   Animal.Type.SPIDER,  Animal.Sex.F, 666,   300,   143_500,  true));
        expected.add(new Animal(null,           null,         null,  0,     0,     0,        false));

        // When
        var actual = AnimalUtils.task16(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test16_InputEmptyAnimalList_ResultEmptyListAnimal() {
        // Given
        List<Animal> inputAnimals = new ArrayList<>();
        List<Animal> expected  = new ArrayList<>();

        // When
        var actual = AnimalUtils.task16(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }


    // Правда ли, что пауки кусаются чаще, чем собаки -> Boolean (если данных для ответа недостаточно, вернуть false)
    @Test
    public void test17_InputAnimalList_ResultFalseIsSpidersBiteMoreOftenThanDogs() {
        // Given
        Boolean expected = false;

        // When
        var actual = AnimalUtils.task17(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test17_InputAnimalList_ResultTrueIsSpidersBiteMoreOftenThanDogs() {
        // Given
        Boolean expected = true;
        inputAnimals.add(new Animal("S1", Animal.Type.SPIDER, Animal.Sex.M, 1, 2, 3, true));

        // When
        var actual = AnimalUtils.task17(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test17_InputEmptyAnimalList_ResultFalse() {
        // Given
        List<Animal> inputAnimals = new ArrayList<>();
        Boolean expected = false;

        // When
        var actual = AnimalUtils.task17(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    // Найти самую тяжелую рыбку в 2-х или более списках -> Animal
    @Test
    public void test18_InputAnimalLists_ResultHeaviestFish() {
        // Given
        List<Animal> animals1 = new ArrayList<>();
        animals1.add(new Animal("F1.1", Animal.Type.FISH, Animal.Sex.M, 0, 0 , 10, false));
        animals1.add(new Animal("C1.1", Animal.Type.CAT, Animal.Sex.M, 1, 1, 200_000_000, false));

        List<Animal> animals2 = new ArrayList<>();
        animals2.add(new Animal("F2.1", Animal.Type.FISH, Animal.Sex.M, 0, 0, 200_000, true));
        animals2.add(new Animal("D2.1", Animal.Type.DOG, Animal.Sex.M, 10, 20, 300, false));
        animals2.add(new Animal("D2.2", Animal.Type.DOG, Animal.Sex.F, 10, 20, 300, false));

        Animal expected = new Animal("F2.1", Animal.Type.FISH, Animal.Sex.M, 0, 0, 200_000, true);

        // When
        var actual = AnimalUtils.task18(inputAnimals, animals1, animals2);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test18_InputEmptyAnimalLists_ResultNull() {
        // Given
        List<Animal> inputAnimals = new ArrayList<>();
        Animal expected = null;

        // When
        var actual = AnimalUtils.task18(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    // Животные, в записях о которых есть ошибки: вернуть имя и список ошибок -> Map<String, Set<ValidationError>>.
    @Test
    public void test19_InputAnimalLists_ResultMapWithAnimalNameAndSetErrors() {
        // Given
        Map<String, Set<ValidationError>> expected = new HashMap<>();

        expected.put("UFO", new HashSet<>(Arrays.asList(
            new ValidationError("sex", "sex is null"),
            new ValidationError("age", "age < 0"),
            new ValidationError("heightInCm", "heightInCm <= 0"),
            new ValidationError("weightInGram", "weightInGram <= 0")
        )));
        expected.put(null, new HashSet<>(Arrays.asList(
            new ValidationError("name", "name is null"),
            new ValidationError("type", "type is null"),
            new ValidationError("sex", "sex is null"),
            new ValidationError("heightInCm", "heightInCm <= 0"),
            new ValidationError("weightInGram", "weightInGram <= 0")
        )));
        expected.put("MinDog", new HashSet<>(Arrays.asList(
            new ValidationError("heightInCm", "heightInCm <= 0"),
            new ValidationError("weightInGram", "weightInGram <= 0")
        )));

        // When
        var actual = AnimalUtils.task19(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test19_InputEmptyAnimalLists_ResultEmptyMap() {
        // Given
        List<Animal> inputAnimals = new ArrayList<>();
        Map<String, Set<ValidationError>> expected = new HashMap<>();

        // When
        var actual = AnimalUtils.task19(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    // Сделать результат предыдущего задания более читабельным:
    // вернуть имя и названия полей с ошибками, объединенные в строку -> Map<String, String>
    @Test
    public void test20_InputAnimalLists_ResultMapWithAnimalNameAndStringErrors() {
        // Given
        Map<String, String> expected = new HashMap<>();

        expected.put(null, """
                Field: {name} Error: {name is null}
                Field: {type} Error: {type is null}
                Field: {sex} Error: {sex is null}
                Field: {heightInCm} Error: {heightInCm <= 0}
                Field: {weightInGram} Error: {weightInGram <= 0}""");

        expected.put("UFO", """
                Field: {sex} Error: {sex is null}
                Field: {age} Error: {age < 0}
                Field: {heightInCm} Error: {heightInCm <= 0}
                Field: {weightInGram} Error: {weightInGram <= 0}""");

        expected.put("MinDog", """
                Field: {heightInCm} Error: {heightInCm <= 0}
                Field: {weightInGram} Error: {weightInGram <= 0}""");

        // When
        var actual = AnimalUtils.task20(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test20_InputEmptyAnimalLists_ResultEmptyMap() {
        // Given
        List<Animal> inputAnimals = new ArrayList<>();
        Map<String, String> expected = new HashMap<>();

        // When
        var actual = AnimalUtils.task20(inputAnimals);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    // Хотел как то проверить все 20 задач на null одним параметризованным тестом и придумал что-то такое,
    // но не уверен, что так делать хорошо
    private static Stream<Runnable> tasks_provideParameters() {
        return Stream.of(
            () -> AnimalUtils.task1(null),
            () -> AnimalUtils.task2(null, 0),
            () -> AnimalUtils.task3(null),
            () -> AnimalUtils.task4(null),
            () -> AnimalUtils.task5(null),
            () -> AnimalUtils.task6(null),
            () -> AnimalUtils.task7(null, 0),
            () -> AnimalUtils.task8(null, 0),
            () -> AnimalUtils.task9(null),
            () -> AnimalUtils.task10(null),
            () -> AnimalUtils.task11(null),
            () -> AnimalUtils.task12(null),
            () -> AnimalUtils.task13(null),
            () -> AnimalUtils.task14(null, 0),
            () -> AnimalUtils.task15(null, 0, 0),
            () -> AnimalUtils.task16(null),
            () -> AnimalUtils.task17(null),
            () -> AnimalUtils.task18(null, null, null),
            () -> AnimalUtils.task19(null),
            () -> AnimalUtils.task20(null)
        );
    }

    @ParameterizedTest
    @MethodSource("tasks_provideParameters")
    public void allTasks_InputRunnableThrowingNullPointerException_ResultNullPointerException(Runnable runnable) {
        assertThatThrownBy(runnable::run)
            .isInstanceOf(NullPointerException.class);
    }
}
