package edu.hw8.task2;

import edu.util.Pair;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectorySearchTask extends RecursiveTask<Pair<List<Path>, Integer>> {
    private final Path root;
    private final int requiredFilesCount;

    public DirectorySearchTask(Path root, int requiredFilesCount) {
        this.root = root;
        this.requiredFilesCount = requiredFilesCount;
    }

    @Override
    protected Pair<List<Path>, Integer> compute() {
        List<Path> filesAndDirectories;
        try (Stream<Path> stream = Files.list(root)) {
            filesAndDirectories = stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int filesNumber = 0;
        List<Path> subdirectories = new ArrayList<>();
        for (Path content : filesAndDirectories) {
            if (Files.isRegularFile(content)) {
                filesNumber++;
            } else if (Files.isDirectory(content)) {
                subdirectories.add(content);
            }
        }

        List<DirectorySearchTask> tasks = new ArrayList<>();
        for (Path subdirectory : subdirectories) {
            var task = new DirectorySearchTask(subdirectory, requiredFilesCount);
            task.fork();
            tasks.add(task);
        }

        List<Path> directoriesWithMoreThanRequiredFilesCount = new ArrayList<>();
        for (var task : tasks) {
            Pair<List<Path>, Integer> result = task.join();

            directoriesWithMoreThanRequiredFilesCount.addAll(result.key());
            filesNumber += result.value();
        }

        if (filesNumber > requiredFilesCount) {
            directoriesWithMoreThanRequiredFilesCount.add(root);
        }

        return new Pair<>(
            directoriesWithMoreThanRequiredFilesCount,
            filesNumber
        );
    }
}
