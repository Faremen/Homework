package edu.project4;

import edu.project4.date.AffineSpace;
import edu.project4.date.FractalImage;
import edu.project4.image.ImageFormat;
import edu.project4.image.ImageUtils;
import edu.project4.render.ConfigRender;
import edu.project4.render.GammaCorrection;
import edu.project4.render.MultiThreadRender;
import edu.project4.render.Renderer;
import edu.project4.transformation.HandkerchiefTransformation;
import edu.project4.transformation.NotTransformation;
import edu.project4.transformation.PolarTransformation;
import edu.project4.transformation.SinTransformation;
import edu.project4.transformation.SphereTransformation;
import edu.project4.transformation.SwirlTransformation;
import edu.project4.transformation.Transformation;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"HideUtilityClassConstructor", "MultipleStringLiterals", "RegexpSinglelineJava", "MagicNumber"})
public class Main {

    private static final int HEIGHT = 1080;
    private static final int WIDTH = 1920;
    private static final int NUMBER_SAMPLES = 20000;
    private static final int ITERATION_PER_SAMPLE = 2000;

    private static final double X_MIN = -1.777;
    private static final double X_MAX = 1.777;
    private static final int Y_MIN = -1;
    private static final int Y_MAX = 1;
    private static final int SYMMETRY = 3;
    private static final int COUNT_THREADS = 8;

    private static final double GAMMA = 2.2;

    private static final int COUNT_IMAGES = 4;
    private static final int COUNT_AFFINE_SPACE = 3;

    private static final List<Transformation> TRANSFORMATION_LIST = List.of(
        new SphereTransformation(),
        new SwirlTransformation(),
        new HandkerchiefTransformation(),
        new PolarTransformation(),
        new NotTransformation(),
        new SinTransformation()
    );

    private static final Renderer RENDERER = new MultiThreadRender(COUNT_THREADS);
//    private static final Renderer RENDERER = new SingleThreadRenderer();

    private Main() {
    }

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < COUNT_IMAGES; i++) {
            FractalImage fractalImage = new FractalImage(WIDTH, HEIGHT);

            List<AffineSpace> affineSpaceList = new ArrayList<>();
            for (int j = 0; j < COUNT_AFFINE_SPACE; j++) {
                affineSpaceList.add(new AffineSpace());
            }

            long start = System.currentTimeMillis();
            RENDERER.render(
                fractalImage,
                affineSpaceList,
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

            Path notGammaFile = Paths.get("src/main/java/edu/project4/Images/image" + i + ".png");

            ImageUtils.save(
                fractalImage,
                notGammaFile,
                ImageFormat.PNG
            );
            System.out.printf("Изображение без гамма коррекции сохранено: %s%n", notGammaFile.toAbsolutePath());
            long end = System.currentTimeMillis();

            GammaCorrection.correct(fractalImage, GAMMA);
            Path gammaFile = Paths.get("src/main/java/edu/project4/Images/imageGamma" + i + ".png");

            ImageUtils.save(
                fractalImage,
                gammaFile,
                ImageFormat.PNG
            );
            System.out.printf("Изображение с гамма коррекцией сохранено: %s%n", gammaFile.toAbsolutePath());

            System.out.printf("Время рендеринга: %.3f сек%n", (end - start) / 1e3);
        }
    }
}
