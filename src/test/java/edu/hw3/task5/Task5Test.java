package edu.hw3.task5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    // Оставлю здесь чтобы было чутка удобнее
    // A B C D E F G H I J K L M N O P Q R S T U V W X Y Z

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
            ),
            Arguments.of(
                SortRule.DESC,
                new String[] {
                    "Paul Erdos",
                    "Leonhard Euler",
                    "Carl Gauss"
                },
                new Person[] {
                    new Person("Carl", "Gauss"),
                    new Person("Leonhard", "Euler"),
                    new Person("Paul", "Erdos")
                }
            ),
            Arguments.of(
                SortRule.ASC,
                new String[] {
                    "A Z",
                    "D F",
                    "C B"
                },
                new Person[] {
                    new Person("C", "B"),
                    new Person("D", "F"),
                    new Person("A", "Z")
                }
            ),
            Arguments.of(
                SortRule.ASC,
                new String[] {
                    "A    Z",
                    "D \n  F",
                    "C\t B"
                },
                new Person[] {
                    new Person("C", "B"),
                    new Person("D", "F"),
                    new Person("A", "Z")
                }
            ),
            Arguments.of(
                SortRule.ASC,
                new String[] {
                    "A Z F D S",
                    "D F PS",
                    "C B wse"
                },
                new Person[] {
                    new Person("C", "B"),
                    new Person("D", "F"),
                    new Person("A", "Z")
                }
            ),
            Arguments.of(
                SortRule.ASC,
                new String[] {
                    "A",
                    "D F",
                    "C B"
                },
                new Person[] {
                    new Person("A", null),
                    new Person("C", "B"),
                    new Person("D", "F")
                }
            ),
            Arguments.of(
                SortRule.ASC,
                new String[] {
                    "A Z",
                    "D",
                    "C B"
                },
                new Person[] {
                    new Person("C", "B"),
                    new Person("D", null),
                    new Person("A", "Z")
                }
            ),
            Arguments.of(
                SortRule.ASC,
                new String[] {
                    "A",
                    "D",
                    "C"
                },
                new Person[] {
                    new Person("A", null),
                    new Person("C", null),
                    new Person("D", null)
                }
            ),
            Arguments.of(
                SortRule.ASC,
                null,
                new Person[] {}
            ),
            Arguments.of(
                SortRule.ASC,
                new String[] {},
                new Person[] {}
            ),
            Arguments.of(
                null,
                new String[] {
                    "A",
                    "D F",
                    "C B"
                },
                new Person[] {}
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
