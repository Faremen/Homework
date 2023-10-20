package edu.hw2.task2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RectangleTest {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    public void rectangleArea(Rectangle rect) {
        rect = rect.setWidth(20).setHeight(10);

        assertThat(rect.area()).isEqualTo(200.0);
    }

    @Test
    public void squareArea() {
        // Given
        Square square = new Square();
        double expectedArea = 400;

        // When
        square = square.setSide(10).setSide(20);
        double actualArea = square.area();

        // Then
        assertThat(actualArea).isEqualTo(expectedArea);
    }
}
