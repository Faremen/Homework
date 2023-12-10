package edu.project4.image;

import edu.project4.date.FractalImage;
import edu.project4.date.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import javax.imageio.ImageIO;

@SuppressWarnings("MultipleStringLiterals")
public class ImageUtils {

    private ImageUtils() {
    }

    public static void save(FractalImage fractalImage, Path filename, ImageFormat imageFormat) throws IOException {
        save(fractalImage.getPixels(), filename, imageFormat);
    }

    public static void save(Pixel[][] pixels, Path filename, ImageFormat imageFormat) throws IOException {
        for (int i = 1; i < pixels.length; i++) {
            if (pixels[i].length != pixels[i - 1].length) {
                throw new IllegalArgumentException("Pixel array must be rectangular");
            }
        }

        int[][] pixelsInt = new int[pixels.length][pixels[0].length];

        for (int i = 0; i < pixelsInt.length; i++) {
            pixelsInt[i] = Arrays.stream(pixels[i]).mapToInt(Pixel::getRGB).toArray();
        }

        save(pixelsInt, filename, imageFormat);
    }

    public static void save(Color[][] colors, Path filename, ImageFormat imageFormat) throws IOException {
        for (int i = 1; i < colors.length; i++) {
            if (colors[i].length != colors[i - 1].length) {
                throw new IllegalArgumentException("Color array must be rectangular");
            }
        }

        int[][] pixels = new int[colors.length][colors[0].length];

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = Arrays.stream(colors[i]).mapToInt(Color::getRGB).toArray();
        }

        save(pixels, filename, imageFormat);
    }

    public static void save(int[][] pixels, Path filename, ImageFormat imageFormat) throws IOException {
        for (int i = 1; i < pixels.length; i++) {
            if (pixels[i].length != pixels[i - 1].length) {
                throw new IllegalArgumentException("Pixel array must be rectangular");
            }
        }

        BufferedImage bufferedImage = createBufferedImage(pixels);
        writeInFile(filename, imageFormat, bufferedImage);
    }

    private static BufferedImage createBufferedImage(int[][] pixels) {
        BufferedImage bufferedImage = new BufferedImage(
            pixels.length,
            pixels[0].length,
            BufferedImage.TYPE_4BYTE_ABGR
        );

        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[0].length; y++) {
                bufferedImage.setRGB(x, y, pixels[x][y]);
            }
        }

        return bufferedImage;
    }

    private static void writeInFile(Path filename, ImageFormat imageFormat, BufferedImage bufferedImage)
        throws IOException {
        Files.deleteIfExists(filename);
        Files.createDirectories(filename.getParent());
        File newFile = Files.createFile(filename).toFile();

        ImageIO.write(bufferedImage, imageFormat.name(), newFile);
    }
}
