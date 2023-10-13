package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @Test
    public void rotateLeft_InputNumber16InputShift1_Result1() {
        // Given
        int inputNumber = 16;   // 10000 -> 00001 (1)
        int inputShift = 1;
        int expectedNumber = 1;

        // When
        int actualNumber = Task7.rotateLeft(inputNumber, inputShift);

        // Then
        assertThat(actualNumber).isEqualTo(expectedNumber);
    }

    @Test
    public void rotateLeft_InputNumber17InputShift2_Result6() {
        // Given
        int inputNumber = 17;   // 10001 -> 00110 (6)
        int inputShift = 2;
        int expectedNumber = 6;

        // When
        int actualNumber = Task7.rotateLeft(inputNumber, inputShift);

        // Then
        assertThat(actualNumber).isEqualTo(expectedNumber);
    }
}
