package edu.project3.receiver.path;

import edu.project3.receiver.Receiver;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PathLogReceiver implements Receiver {
    private final List<Path> paths;

    public PathLogReceiver(List<Path> paths) {
        this.paths = paths;
    }

    @Override
    public List<String> receive() {
        List<String> listLogInfo = new ArrayList<>();

        for (Path path : paths) {
            try {
                listLogInfo.addAll(Files.readAllLines(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return listLogInfo;
    }
}
