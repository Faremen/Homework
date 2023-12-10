package edu.project4;

import edu.project4.date.AffineSpace;
import edu.project4.date.FractalImage;
import edu.project4.image.ImageFormat;
import edu.project4.image.ImageUtils;
import edu.project4.render.ConfigRender;
import edu.project4.render.MultiThreadRender;
import edu.project4.render.Renderer;
import edu.project4.transformation.HandkerchiefTransformation;
import edu.project4.transformation.SinTransformation;
import edu.project4.transformation.SphereTransformation;
import edu.project4.transformation.SwirlTransformation;
import edu.project4.transformation.Transformation;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PerformanceTest {

    private static final int HEIGHT = 1080;
    private static final int WIDTH = 1920;
    private static final int NUMBER_SAMPLES = 20000;

    private static final int ITERATION_PER_SAMPLE = 2000;
    private static final double X_MIN = -1.777;
    private static final double X_MAX = 1.777;
    private static final int Y_MIN = -1;
    private static final int Y_MAX = 1;
    private static final int SYMMETRY = 2;

    private static final Path FILENAME = Paths.get("src/test/java/edu/project4/TestImages/testPerformanceImage.png");

    private static final ImageFormat IMAGE_FORMAT = ImageFormat.PNG;

    private static final List<Transformation> TRANSFORMATION_LIST = List.of(
        new SinTransformation(),
        new SphereTransformation(),
        new SwirlTransformation(),
        new HandkerchiefTransformation()
    );

    private static final List<AffineSpace> AFFINE_SPACE_LIST = List.of(
        new AffineSpace(),
        new AffineSpace(),
        new AffineSpace()
    );

    @ParameterizedTest
    @ValueSource(ints = {
        1, 2, 3, 4, 6, 8
    })
    public void performance(int countThread) throws IOException {
        printPerformance(new MultiThreadRender(countThread), countThread);
    }

    private static void printPerformance(Renderer renderer, int countThreads) throws IOException {
        FractalImage fractalImage = new FractalImage(WIDTH, HEIGHT);

        long start = System.currentTimeMillis();
        renderer.render(
            fractalImage,
            AFFINE_SPACE_LIST,
            TRANSFORMATION_LIST,
            NUMBER_SAMPLES,
            ITERATION_PER_SAMPLE,
            new ConfigRender(
                X_MIN,
                X_MAX,
                Y_MIN,
                Y_MAX,
                SYMMETRY
            )
        );
        long end = System.currentTimeMillis();

        ImageUtils.save(fractalImage, FILENAME, IMAGE_FORMAT);

        System.out.printf("Потоков: %d%n", countThreads);
        System.out.printf("Время рендеринга: %.3f сек%n", (end - start) / 1e3);
    }
}
