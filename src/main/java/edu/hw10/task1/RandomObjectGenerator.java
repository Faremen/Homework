package edu.hw10.task1;

import edu.hw10.task1.generator.Generator;
import edu.hw10.task1.generator.random.RandomDoubleGenerator;
import edu.hw10.task1.generator.random.RandomIntGenerator;
import edu.hw10.task1.generator.random.RandomStringGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class RandomObjectGenerator {

    private final Map<Class<?>, Generator> generators = new HashMap<>() {{
        put(int.class, new RandomIntGenerator());
        put(double.class, new RandomDoubleGenerator());
        put(String.class, new RandomStringGenerator());
    }};

    public Generator putGenerator(Class<?> curentClass, Generator generator) {
        return generators.put(curentClass, generator);
    }

    public Set<Map.Entry<Class<?>, Generator>> getGenerators() {
        return generators.entrySet();
    }

    public <T> T nextObject(Class<T> curentClass)
        throws InvocationTargetException, IllegalAccessException, InstantiationException {
        return nextObject(curentClass, null);
    }

    public <T> T nextObject(Class<T> curentClass, String methodName)
        throws InvocationTargetException, IllegalAccessException, InstantiationException {
        if (methodName != null) {
            Method method = getMethod(curentClass, methodName);

            if (method == null) {
                throw new IllegalArgumentException("No methods with name %s".formatted(methodName));
            }

            if (!Modifier.isStatic(method.getModifiers())) {
                throw new IllegalArgumentException("Method must be static: %s".formatted(method));
            }

            method.setAccessible(true);
            return (T) method.invoke(null, getArguments(method.getParameters()));
        }

        Constructor<T> constructor = getConstructor(curentClass);
        constructor.setAccessible(true);
        return constructor.newInstance(getArguments(constructor.getParameters()));
    }

    private <T> Constructor<T> getConstructor(Class<T> currentClass) {
        var constructors = currentClass.getConstructors();

        if (constructors.length == 0) {
            throw new IllegalArgumentException("No constructors: %s".formatted(currentClass));
        }

        return (Constructor<T>) constructors[ThreadLocalRandom.current().nextInt(constructors.length)];
    }

    private Method getMethod(Class<?> currentClass, String methodName) {
        List<Method> methods = new ArrayList<>();

        for (Method method : currentClass.getDeclaredMethods()) {
            if (method.getName().equals(methodName)) {
                methods.add(method);
            }
        }

        if (!methods.isEmpty()) {
            return methods.get(ThreadLocalRandom.current().nextInt(methods.size()));
        }

        return null;
    }

    private Object[] getArguments(Parameter[] parameters) {
        Object[] result = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            Generator generator = generators.get(parameters[i].getType());

            if (generator == null) {
                throw new IllegalArgumentException("No generator for the type: %s".formatted(
                    parameters[i].getType().getName()));
            }

            result[i] = generator.generate(parameters[i]);
        }

        return result;
    }
}
