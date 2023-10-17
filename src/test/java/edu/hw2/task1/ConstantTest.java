package edu.hw2.task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConstantTest {

    @ParameterizedTest
    @CsvSource(value = {
        "1,     1",
        "0,     0",
        "-10,   -10",
        "1.1,   1.1",
        "0.01,  0.01"
    })
    public void evaluate_InputNumber_ResultNumber(double inputNumber, double expectedNumber) {
        // When
        Constant constant = new Constant(inputNumber);
        double actualNumber = constant.evaluate();

        // Then
        assertThat(actualNumber).isEqualTo(expectedNumber);
    }
}
