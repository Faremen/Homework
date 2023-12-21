package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileSearchTask extends RecursiveTask<List<Path>> {
    private final Path root;
    private final Predicate<Path> predicate;

    public FileSearchTask(Path root, Predicate<Path> predicate) {
        this.root = root;
        this.predicate = predicate;
    }

    @Override
    protected List<Path> compute() {
        List<Path> filesAndDirectories;
        try (var stream = Files.list(root)) {
            filesAndDirectories = stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Path> files = new ArrayList<>();
        List<Path> subdirectories = new ArrayList<>();
        for (Path content : filesAndDirectories) {
            if (Files.isRegularFile(content)) {
                files.add(content);
            } else if (Files.isDirectory(content)) {
                subdirectories.add(content);
            }
        }

        List<Path> requiredFiles = new ArrayList<>();
        for (Path file : files) {
            if (predicate.test(file)) {
                requiredFiles.add(file);
            }
        }

        List<FileSearchTask> tasks = new ArrayList<>();
        for (Path subdirectory : subdirectories) {
            var task = new FileSearchTask(subdirectory, predicate);
            task.fork();
            tasks.add(task);
        }

        for (var task : tasks) {
            requiredFiles.addAll(task.join());
        }

        return requiredFiles;
    }
}
