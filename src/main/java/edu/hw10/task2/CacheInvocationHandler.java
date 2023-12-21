package edu.hw10.task2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import static java.nio.file.StandardOpenOption.CREATE;

public class CacheInvocationHandler implements InvocationHandler {

    private final Object target;
    private final Path cacheFile;
    private Map<String, Map<List<Object>, Object>> inMemoryCache = new HashMap<>();

    public CacheInvocationHandler(Object target, Path cacheFile) {
        this.target = target;
        this.cacheFile = cacheFile;

        if (Files.exists(cacheFile)) {
            readCacheFile();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.setAccessible(true);

        List<Object> parameters = Arrays.asList(args);
        if (method.isAnnotationPresent(Cache.class)) {
            Object result;
            if (inMemoryCache.containsKey(method.toString())) {
                var results = inMemoryCache.get(method.toString());

                if (results.containsKey(parameters)) {
                    return results.get(parameters);
                }

                result = method.invoke(target, args);
                results.put(parameters, result);
            } else {
                result = method.invoke(target, args);

                inMemoryCache.put(method.toString(), Map.of(parameters, result));
            }

            if (isPersist(method)) {
                writeCacheFile();
            }

            return result;
        }

        return method.invoke(target, args);
    }

    private void readCacheFile() {
        if (Files.exists(cacheFile)) {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(cacheFile))) {
                if (ois.readObject() instanceof Map<?, ?> obj) {
                    inMemoryCache = (Map<String, Map<List<Object>, Object>>) obj;
                }
            } catch (ClassNotFoundException | IOException e) {
                LogManager.getLogger().info(e.getMessage());
            }
        }
    }

    private void writeCacheFile() {
        if (!inMemoryCache.isEmpty()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(cacheFile, CREATE))) {
                oos.writeObject(inMemoryCache);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isPersist(Method method) {
        Cache cache = method.getAnnotation(Cache.class);
        return cache != null && cache.persist();
    }
}
