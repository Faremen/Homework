package edu.project2;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@SuppressWarnings("RegexpSinglelineJava")
public class ConsoleMazePrinter implements MazePrinter {

    private static final String ANSI_RESET_COLOR = "\u001B[0m";
    private static final String ANSI_WALL_COLOR = "\u001b[48;5;16m";
    private static final String ANSI_PATH_COLOR = "\u001b[48;5;22m";

    private final int cellHeight;
    private final int cellWidth;
    private final int wallHeight;
    private final int wallWidth;

    public ConsoleMazePrinter(int cellHeight, int cellWidth, int wallHeight, int wallWidth) {
        this.cellHeight = cellHeight;
        this.cellWidth = cellWidth;
        this.wallHeight = wallHeight;
        this.wallWidth = wallWidth;
    }

    @Override
    public void print(Maze maze) {
        Objects.requireNonNull(maze);

        StringBuilder stringBuilder = new StringBuilder();

        renderWallLine(maze, stringBuilder);

        for (int i = 1; i < maze.getHeight() * 2; i++) {
            if (i % 2 == 1) {
                renderVerticalWalls(maze, stringBuilder, i);
            } else {
                renderHorizontalWalls(maze, stringBuilder, i);
            }
        }

        renderWallLine(maze, stringBuilder);

        System.out.println(stringBuilder);
    }

    @Override
    public void print(Maze maze, List<Coordinate> path) {
        Objects.requireNonNull(maze);
        Objects.requireNonNull(path);

        Set<Coordinate> coordinateSet = new HashSet<>(path);

        StringBuilder stringBuilder = new StringBuilder();

        renderWallLine(maze, stringBuilder);

        for (int i = 1; i < maze.getHeight() * 2; i++) {
            if (i % 2 == 1) {
                renderVerticalWalls(maze, stringBuilder, i);
            } else {
                renderHorizontalWalls(maze, stringBuilder, i);
            }
        }

        renderWallLine(maze, stringBuilder);

        System.out.println(stringBuilder);
    }

    private void renderVerticalWalls(Maze maze, StringBuilder stringBuilder, int i) {
        for (int j = 0; j < cellHeight; j++) {
            renderOneWall(wallWidth, stringBuilder);
            for (int k = 1; k < maze.getWidth() * 2; k++) {
                if (k % 2 == 1) {
                    renderOneCell(cellWidth, stringBuilder);
                } else {
                    if (maze.getCell(i / 2, k / 2 - 1).isRightWall()) {
                        renderOneWall(wallWidth, stringBuilder);
                    } else {
                        renderOneCell(wallWidth, stringBuilder);
                    }
                }
            }
            renderOneWall(wallWidth, stringBuilder);
            stringBuilder.append("\n");
        }
    }

    private void renderHorizontalWalls(Maze maze, StringBuilder stringBuilder, int i) {
        for (int j = 0; j < wallHeight; j++) {
            renderOneWall(wallWidth, stringBuilder);
            for (int k = 1; k < maze.getWidth() * 2; k++) {
                if (k % 2 != 1) {
                    renderOneWall(wallWidth, stringBuilder);
                } else {
                    if (maze.getCell(i / 2 - 1, k / 2).isBottomWall()) {
                        renderOneWall(cellWidth, stringBuilder);
                    } else {
                        renderOneCell(cellWidth, stringBuilder);
                    }
                }
            }
            renderOneWall(wallWidth, stringBuilder);
            stringBuilder.append("\n");
        }
    }

    private void renderWallLine(Maze maze, StringBuilder stringBuilder) {
        for (int i = 0; i < wallHeight; i++) {
            for (int j = 0; j < maze.getWidth() * 2 + 1; j++) {
                if (j % 2 == 0) {
                    renderOneWall(wallWidth, stringBuilder);
                } else {
                    renderOneWall(cellWidth, stringBuilder);
                }
            }
            stringBuilder.append("\n");
        }
    }

    private void renderOneWall(int wallWidth, StringBuilder stringBuilder) {
        stringBuilder.append((ANSI_WALL_COLOR + " " + ANSI_RESET_COLOR).repeat(wallWidth));
    }

    private void renderOneCell(int cellWidth, StringBuilder stringBuilder) {
        stringBuilder.append(" ".repeat(cellWidth));
    }

    private void renderOnePath(int wallWidth, StringBuilder stringBuilder) {
        stringBuilder.append((ANSI_PATH_COLOR + " " + ANSI_RESET_COLOR).repeat(wallWidth));
    }
}
