package edu.project4.render;

import edu.project4.date.AffineSpace;
import edu.project4.date.FractalImage;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.Random;

public class SingleThreadRenderer extends Renderer {

    private Random random;

    public SingleThreadRenderer(int seed) {
        this.random = new Random(seed);
    }

    public SingleThreadRenderer() {
        random = new Random();
    }

    @Override
    public void render(
        FractalImage fractalImage,
        List<AffineSpace> affineSpaces,
        List<Transformation> transformationList,
        int numberSamples,
        int iterationPerSample,
        ConfigRender configRender
    ) {
        for (int i = 0; i < numberSamples; i++) {
            pickPointAndTransform(
                fractalImage,
                affineSpaces.get(random.nextInt(0, affineSpaces.size())),
                transformationList.get(random.nextInt(0, transformationList.size())),
                iterationPerSample,
                configRender
            );
        }
    }
}
