package edu.project4.date;

import java.awt.Color;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class FractalImageTest {

    private static final int WIDTH = 30;
    private static final int HEIGHT = 50;
    public FractalImage fractalImage;

    @BeforeEach
    public void setUp() {
        fractalImage = new FractalImage(WIDTH, HEIGHT);
    }

    private static Stream<Arguments> isContains_InputXAndY_ProvideParameters() {
        return Stream.of(
            Arguments.of(0, 0, true),
            Arguments.of(10, 20, true),
            Arguments.of(0, -1, false),
            Arguments.of(WIDTH, 0, false),
            Arguments.of(WIDTH, HEIGHT, false),
            Arguments.of(WIDTH - 1, HEIGHT - 1, true)
        );
    }

    @ParameterizedTest
    @MethodSource("isContains_InputXAndY_ProvideParameters")
    public void isContains_InputXAndY_ResultExpectedBoolean(int x, int y, boolean expected) {
        // When
        var actual = fractalImage.isContains(x, y);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> isContains_Point_ProvideParameters() {
        return Stream.of(
            Arguments.of(new Point(0, 0), true),
            Arguments.of(new Point(10, 20), true),
            Arguments.of(new Point(0, -1), false),
            Arguments.of(new Point(WIDTH, 0), false),
            Arguments.of(new Point(WIDTH, HEIGHT), false),
            Arguments.of(new Point(WIDTH - 1, HEIGHT - 1), true)
        );
    }

    @ParameterizedTest
    @MethodSource("isContains_Point_ProvideParameters")
    public void isContains_Point_ResultExpectedBoolean(Point point, boolean expected) {
        // When
        var actual = fractalImage.isContains(point);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> getPixel_InputXAndY_ProvideParameters() {
        return Stream.of(
            Arguments.of(0, 0, new Pixel(new Color(0), 0)),
            Arguments.of(10, 20, new Pixel(new Color(0), 0))
        );
    }

    @ParameterizedTest
    @MethodSource("getPixel_InputXAndY_ProvideParameters")
    public void getPixel_InputXAndY_ResultExpectedPixel(int x, int y, Pixel expected) {
        // When
        var actual = fractalImage.getPixel(x, y);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> getPixel_Point_ProvideParameters() {
        return Stream.of(
            Arguments.of(new Point(0, 0), new Pixel(new Color(0), 0)),
            Arguments.of(new Point(10, 20), new Pixel(new Color(0), 0))
        );
    }

    @ParameterizedTest
    @MethodSource("getPixel_Point_ProvideParameters")
    public void getPixel_Point_ResultExpectedPixel(Point point, Pixel expected) {
        // When
        var actual = fractalImage.getPixel(point);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
