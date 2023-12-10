package edu.project4.render;

import edu.project4.date.AffineSpace;
import edu.project4.date.FractalImage;
import edu.project4.date.Pixel;
import edu.project4.date.Point;
import edu.project4.transformation.Transformation;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Renderer {

    abstract public void render(
        FractalImage fractalImage,
        List<AffineSpace> affineSpaces,
        List<Transformation> transformationList,
        int numberSamples,
        int iterationPerSample,
        ConfigRender configRender
    );

    @SuppressWarnings("MagicNumber")
    public void pickPointAndTransform(
        FractalImage fractalImage,
        AffineSpace affineSpace,
        Transformation transformation,
        int iterationPerSample,
        ConfigRender configRender
    ) {
        for (int step = -20; step < iterationPerSample; step++) {
            symmetryTransformations(
                fractalImage,
                affineSpace,
                transformation.apply(pickAffineSpacePoint(affineSpace, configRender)),
                step,
                configRender
            );
        }
    }

    private Point pickAffineSpacePoint(AffineSpace affineSpace, ConfigRender configRender) {
        Point randomPoint = new Point(
            ThreadLocalRandom.current().nextDouble(
                configRender.xMin(),
                configRender.xMax()
            ),
            ThreadLocalRandom.current().nextDouble(
                configRender.yMin(),
                configRender.yMax()
            )
        );

        return affineSpace.calcPoint(randomPoint);
    }

    private void symmetryTransformations(
        FractalImage fractalImage,
        AffineSpace affineSpace,
        Point point,
        int step,
        ConfigRender configRender
    ) {
        double theta;
        if (step > 0) {
            theta = 0;
            for (int s = 0; s < configRender.symmetry(); s++) {
                theta += 2 * Math.PI / configRender.symmetry();

                Point rotPoint = getSymmetryPoint(point, theta);

                if (rotPoint.x() >= configRender.xMin() && rotPoint.x() <= configRender.xMax()
                    && rotPoint.y() >= configRender.yMin() && rotPoint.y() <= configRender.yMax()) {
                    Point resultPoint = getResultPoint(
                        rotPoint,
                        fractalImage.getWidth(),
                        fractalImage.getHeight(),
                        configRender.xMin(),
                        configRender.xMax(),
                        configRender.yMin(),
                        configRender.yMax()
                    );

                    if (fractalImage.isContains(resultPoint)) {
                        synchronized (fractalImage.getPixel(resultPoint)) {
                            changeColor(fractalImage.getPixel(resultPoint), affineSpace);
                        }
                    }
                }
            }
        }
    }

    private Point getSymmetryPoint(Point point, double theta) {
        return new Point(
            point.x() * Math.cos(theta) - point.y() * Math.sin(theta),
            point.x() * Math.sin(theta) + point.y() * Math.cos(theta)
        );
    }

    private Point getResultPoint(
        Point point, double xRes, double yRes, double xMIN, double xMAX, double yMIN, double yMAX
    ) {
        return new Point(
            xRes - (int) (((xMAX - point.x()) / (xMAX - xMIN)) * xRes),
            yRes - (int) (((yMAX - point.y()) / (yMAX - yMIN)) * yRes)
        );
    }

    private void changeColor(Pixel pixel, AffineSpace affineSpace) {
        pixel.setColor(new Color(
            (pixel.getRed() + affineSpace.getRed()) / 2,
            (pixel.getGreen() + affineSpace.getGreen()) / 2,
            (pixel.getBlue() + affineSpace.getBlue()) / 2
        ));

        pixel.incHitCount();
    }
}
