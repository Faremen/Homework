package edu.hw6;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

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

    public static void deleteFile(Path path) {
        try {
            Files.deleteIfExists(path);
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
}
