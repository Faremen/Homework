package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task3Test {

    @Test
    public void isNestableTrue() {
        // Given
        int[] firstInputArr = new int[] {1, 2, 3, 4};
        int[] secondInputArr = new int[] {0, 6};

        // When
        boolean actualIsNestable = Task3.isNestable(firstInputArr, secondInputArr);

        // Then
        assertThat(actualIsNestable).isTrue();
    }

    @Test
    public void isNestableFalse() {
        // Given
        int[] firstInputArr = new int[] {9, 9, 8};
        int[] secondInputArr = new int[] {8, 9};

        // When
        boolean actualIsNestable = Task3.isNestable(firstInputArr, secondInputArr);

        // Then
        assertThat(actualIsNestable).isFalse();
    }

    @Test
    public void isNestableEmptyArrays() {
        // Given
        int[] firstInputArr = new int[] {};
        int[] secondInputArr = new int[] {};

        assertThatThrownBy(() -> {
            Task3.isNestable(firstInputArr, secondInputArr);
        }).isInstanceOf(IndexOutOfBoundsException.class);
    }
}
