package edu.project4.image;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageUtilsTest {

    @Test
    public void save_InputNotRectangularArray_ResultIllegalArgumentException() {
        // Given
        Path filename = Paths.get("");
        ImageFormat imageFormat = ImageFormat.PNG;
        int[][] pixels = {
            {1, 3, 4},
            {1, 3},
            {3}
        };

        assertThatThrownBy(() -> ImageUtils.save(pixels, filename, imageFormat))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Pixel array must be rectangular");
    }

    @Test
    public void save_InputPixels_ResultCreatedImage() throws IOException {
        // Given
        boolean expected = true;

        Path filename = Paths.get("src/test/java/edu/project4/TestImages/testImage.png");
        ImageFormat imageFormat = ImageFormat.PNG;

        Color color = new Color(150, 220, 69);
        int width = 300;
        int height = 600;
        Color[][] pixels = new Color[width][height];

        // When
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y] = color;
            }
        }

        ImageUtils.save(pixels, filename, imageFormat);

        var actual = Files.exists(filename);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
