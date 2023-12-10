package edu.hw9.task2;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;

public final class SearchUtils {
    private SearchUtils() {
    }

    public static List<Path> findDirectories(Path root, int requiredFilesCount) {
        try (var forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(
                new DirectorySearchTask(root, requiredFilesCount)
            ).key();
        }
    }

    public static List<Path> findFiles(Path root, Predicate<Path> predicate) {
        try (var forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(
                new FileSearchTask(root, predicate)
            );
        }
    }
}
