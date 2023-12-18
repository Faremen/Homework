package edu.util;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {

    private FileUtil() {}

    public static void createFile(Path path) {
        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException ignored) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createDirectories(Path path) {
        try {
            Files.createDirectories(path);
        } catch (FileAlreadyExistsException ignored) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteFile(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteFiles(List<Path> paths) {
        try {
            for (var path : paths) {
                Files.deleteIfExists(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void appendInFile(Path path, String str) {
        try {
            Files.write(path, str.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readAllLines(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readAllLinesBreak(Path path) {
        try {
            List<String> lines = Files.readAllLines(path);

            return lines.stream()
                .map((line) -> line.replaceAll("\\\\n", "\n"))
                .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void copyFile(Path source, Path target) {
        try {
            Files.copy(source, target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void recursiveDelete(Path root) throws IOException {
        if (!Files.exists(root)) {
            return;
        }

        List<Path> filesAndDirectories;
        if (Files.isDirectory(root)) {
            try (var stream = Files.list(root)) {
                filesAndDirectories = stream.collect(Collectors.toList());

                for (Path p : filesAndDirectories) {
                    recursiveDelete(p);
                }
            }
        }

        deleteFile(root);
    }

    public static void recursiveCreate(Path root, int depth, int directoryCount, int filesCount, String extension) {
        if (!Files.exists(root)) {
            createDirectories(root);
        }

        if (depth == 0) {
            for (int i = 0; i < filesCount; i++) {
                createFile(root.resolve(i + "." + extension));
            }
            return;
        }

        for (int i = 0; i < directoryCount; i++) {
            recursiveCreate(root.resolve(String.valueOf(i)), depth - 1, directoryCount, filesCount, extension);
        }
    }
}
