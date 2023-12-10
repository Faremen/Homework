package edu.hw6.task2;

import edu.util.FileUtil;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task2 {

    private final static String EXTENSION_SEPARATOR = "\\.";

    private final static String COPY_NAME = " — копия";

    private Task2() {}

    public static Path cloneFile(Path path) {
        if (!Files.exists(path)) {
            return null;
        }

        Path directory = path.getParent();
        String fileName = path.getFileName().toString();

        String fileNameWithoutExtension;
        String extension;
        if (Files.isDirectory(path)) {
            fileNameWithoutExtension = fileName;
            extension = "";
        } else {
            fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf("."));
            extension = fileName.substring(fileName.lastIndexOf("."));
        }

        return copyFile(path, fileNameWithoutExtension, extension, directory);
    }

    private static Path copyFile(Path source, String fileNameWithoutExtension, String extension, Path directory) {
        String copyFileName = fileNameWithoutExtension + COPY_NAME  + extension;
        Path copyFilePath = directory.resolve(copyFileName);

        int copyCount = 1;
        while (Files.exists(copyFilePath)) {
            copyCount++;
            copyFileName = fileNameWithoutExtension + COPY_NAME + " (" + copyCount + ")" + extension;
            copyFilePath = directory.resolve(copyFileName);
        }

        FileUtil.copyFile(source, copyFilePath);

        return copyFilePath;
    }
}
