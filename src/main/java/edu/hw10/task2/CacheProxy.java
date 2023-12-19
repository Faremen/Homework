package edu.hw10.task2;

import java.lang.reflect.Proxy;
import java.nio.file.Path;

public class CacheProxy {

    private CacheProxy() {
    }

    public static <T> T create(T target, Class<? extends T> targetClass, Path cacheFile) {
        return (T) Proxy.newProxyInstance(
            targetClass.getClassLoader(),
            targetClass.getInterfaces(),
            new CacheInvocationHandler(target, cacheFile)
        );
    }
}
