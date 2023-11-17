package edu.hw6.task1;

import edu.hw6.FileUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private static final String KEY_VALUE_SEPARATOR = ":";
    private final Path pathKeyValueStorage;
    private final Map<String, String> diskMap = new HashMap<>();

    public DiskMap(String fileKeyValueStorage) {
        this(Paths.get(fileKeyValueStorage));
    }

    public DiskMap(Path pathKeyValueStorage) {
        this.pathKeyValueStorage = pathKeyValueStorage;

        createFileKeyValueStorage();
        initDiskMap();
    }

    @Override
    public int size() {
        return diskMap.size();
    }

    @Override
    public boolean isEmpty() {
        return diskMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return diskMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return diskMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return diskMap.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        String prevValue = diskMap.put(key, value);

        if (prevValue == null) {
            writeNewEntry(key, value);
        } else {
            rewriteDiskMap();
        }

        return prevValue;
    }

    @Override
    public String remove(Object key) {
        String prevValue = diskMap.remove(key);

        if (prevValue != null) {
            rewriteDiskMap();
        }

        return prevValue;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        diskMap.putAll(m);
        rewriteDiskMap();
    }

    @Override
    public void clear() {
        deleteFileKeyValueStorage();
        createFileKeyValueStorage();
        diskMap.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return diskMap.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return diskMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return diskMap.entrySet();
    }

    private void rewriteDiskMap() {
        deleteFileKeyValueStorage();
        createFileKeyValueStorage();
        writeDiskMap();
    }

    private void writeDiskMap() {
        StringBuilder stringBuilder = new StringBuilder();

        diskMap.forEach((key, value) -> {
            stringBuilder.append(createEntry(key, value));
        });

        FileUtil.appendInFile(pathKeyValueStorage, stringBuilder.toString());
    }

    private void writeNewEntry(String key, String value) {
        FileUtil.appendInFile(pathKeyValueStorage, createEntry(key, value));
    }

    private String createEntry(String key, String value) {
        return (key + KEY_VALUE_SEPARATOR + value).replaceAll("\n", "\\\\n") + "\n";
    }

    private void deleteFileKeyValueStorage() {
        FileUtil.deleteFile(pathKeyValueStorage);
    }

    private void createFileKeyValueStorage() {
        FileUtil.createFile(pathKeyValueStorage);
    }

    private void initDiskMap() {
        var lines = FileUtil.readAllLinesBreak(pathKeyValueStorage);

        lines.forEach((str) -> {
            String[] keyValue = str.split(KEY_VALUE_SEPARATOR, 2);

            if (keyValue.length >= 2) {
                diskMap.put(keyValue[0], keyValue[1]);
            }
        });
    }
}
