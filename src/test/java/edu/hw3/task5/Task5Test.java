package edu.hw3.task5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class Task5Test {

    private static Stream<Arguments> parseContacts_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                SortRule.ASC,
                new String[] {
                    "John   Locke",
                    "Thomas Aquinas",
                    "David  Hume",
                    "Rene   Descartes"
                },
                new Person[] {
                    new Person("Thomas", "Aquinas"),
                    new Person("Rene", "Descartes"),
                    new Person("David", "Hume"),
                    new Person("John", "Locke")
                }
            ),
            Arguments.of(
                SortRule.DESC,
                new String[] {
                    "John   Locke",
                    "Thomas Aquinas",
                    "David  Hume",
                    "Rene   Descartes"
                },
                new Person[] {
                    new Person("John", "Locke"),
                    new Person("David", "Hume"),
                    new Person("Rene", "Descartes"),
                    new Person("Thomas", "Aquinas")
                }
            )
        );
    }

    @ParameterizedTest
    @MethodSource("parseContacts_ProvideParameters")
    public void parseContacts_InputSortRuleAndStringArray_ResultSortedPersonArray(
        SortRule sortRule,
        String[] inputStrings,
        Person[] expected
    ) {

        // When
        Person[] actual = Task5.parseContacts(inputStrings, sortRule);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
