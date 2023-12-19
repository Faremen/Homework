package edu.hw10.task1.generator.random;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.generator.Generator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDoubleGenerator implements Generator {
    @Override
    public Object generate(Parameter parameter) {
        double min = Integer.MIN_VALUE;
        double max = Integer.MAX_VALUE;

        for (Annotation annotation : parameter.getAnnotations()) {
            if (annotation instanceof Min minAnn) {
                min = (int) minAnn.value();
            }
            if (annotation instanceof Max maxAnn) {
                max = (int) maxAnn.value();
            }
        }

        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
