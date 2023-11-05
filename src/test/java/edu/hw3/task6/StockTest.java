package edu.hw3.task6;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class StockTest {

    @Test
    public void createStock_InputNegativePrice_ResultIllegalArgumentException() {
        assertThatThrownBy(() -> {
            new Stock(-100);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("prise must be greater than 0");
    }
}
