package edu.hw6.task3;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.PathMatcher;
import java.util.regex.Pattern;

public class Filters {

    private Filters() {
    }

    public static AbstractFilter readable() {
        return Files::isReadable;
    }

    public static AbstractFilter writable() {
        return Files::isWritable;
    }

    public static AbstractFilter regularFile() {
        return Files::isRegularFile;
    }

    public static AbstractFilter largerThan(long sizeInBytes) {
        return file -> Files.size(file) > sizeInBytes;
    }

    public static AbstractFilter globMatches(String pattern) {
        PathMatcher matcher = FileSystems.getDefault()
            .getPathMatcher("glob:" + pattern);

        return path -> matcher.matches(path.getFileName());
    }

    public static AbstractFilter regexContains(String regex) {
        Pattern pattern = Pattern.compile(regex);

        return path -> pattern.matcher(path.getFileName().toString()).find();
    }

    public static AbstractFilter magicNumber(byte... bytes) {
        return path -> {
            if (Files.isDirectory(path)) {
                return false;
            }

            byte[] fileBytes = Files.readAllBytes(path);

            if (bytes .length > fileBytes .length) {
                return false;
            }

            for (int j = 0; j < bytes.length; j++) {
                if (fileBytes[j] != bytes[j]) {
                    return false;
                }
            }

            return true;
        };
    }
}
