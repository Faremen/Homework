package edu.hw7.task2;

import java.math.BigInteger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task2Test {

    @ParameterizedTest
    @CsvSource(value = {
        "0, 1",
        "1, 1",
        "5, 120",
        "10, 3_628_800",
        "20, 2_432_902_008_176_640_000",
        "70, 11_978_571_669_969_891_796_072_783_721_689_098_736_458_938_142_546_425_857_555_362_864_628_009_582_789_845_319_680_000_000_000_000_000"
    })
    public void factorialInParallel_InputNum_ResultFactorial(int num, BigInteger expected) {
        // When
        var actual = Task2.factorialInParallel(num);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "-1",
        "-10"
     })
    public void factorialInParallel_InputIncorrectNum_ResultIllegalArgumentException(int num) {
        assertThatThrownBy(() -> Task2.factorialInParallel(num))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("num must be > 0");
    }
}
