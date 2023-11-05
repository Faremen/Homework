package edu.project2;

import java.util.List;
import java.util.Set;

public interface MazePrinter {
    void print(Maze maze);

    void print(Maze maze, List<Coordinate> path);
}
