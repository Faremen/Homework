package edu.project2;

import java.util.List;

public interface MazePrinter {
    void print(Maze maze);

    void print(Maze maze, List<Coordinate> path);
}
