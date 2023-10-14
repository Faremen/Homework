package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task3Test {

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {0, 6}, true),
            Arguments.of(new int[] {3, 1}, new int[] {4, 0}, true),
            Arguments.of(new int[] {9, 9, 8}, new int[] {8, 9}, false),
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {2, 3}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    public void isNestable_InputTwoArrays_ResultCanFirstArrayNestedInSecondArray(
        int[] firstArr,
        int[] secondArr,
        boolean expectedIsNestable
    ) {
        // When
        boolean actualIsNestable = Task3.isNestable(firstArr, secondArr);

        // Then
        assertThat(actualIsNestable).isEqualTo(expectedIsNestable);
    }

    @Test
    public void isNestable_InputTwoEmptyArrays_ThrowIndexOutOfBoundsException() {
        // Given
        int[] firstInputArr = new int[] {};
        int[] secondInputArr = new int[] {};

        assertThatThrownBy(() -> {
            Task3.isNestable(firstInputArr, secondInputArr);
        }).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void isNestable_InputTwoNullArrays_ThrowNullPointerException() {
        // Given
        int[] firstInputArr = null;
        int[] secondInputArr = null;

        assertThatThrownBy(() -> {
            Task3.isNestable(firstInputArr, secondInputArr);
        }).isInstanceOf(NullPointerException.class);
    }
}
