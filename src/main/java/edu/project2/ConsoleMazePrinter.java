package edu.project2;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("RegexpSinglelineJava")
public class ConsoleMazePrinter implements MazePrinter {

    private static final String ANSI_RESET_COLOR = "\u001B[0m";
    private static final String ANSI_WALL_COLOR = "\u001b[48;5;16m";
    private static final String ANSI_PATH_COLOR = "";

    private int cellHeight;
    private int cellWidth;

    public ConsoleMazePrinter(int cellHeight, int cellWidth) {
        this.cellHeight = cellHeight;
        this.cellWidth = cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    @Override
    public void print(Maze maze) {
        Objects.requireNonNull(maze);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int l = 0; l < cellHeight; l++) {
                renderRow(maze, i, stringBuilder);
            }
        }

        System.out.println(stringBuilder);
    }

    private void renderRow(Maze maze, int i, StringBuilder stringBuilder) {
        for (int j = 0; j < maze.getWidth(); j++) {
            for (int k = 0; k < cellWidth; k++) {
                renderCell(maze, i, stringBuilder, j);
            }
        }
        stringBuilder.append("\n");
    }

    private void renderCell(Maze maze, int i, StringBuilder stringBuilder, int j) {
        if (maze.getCell(i, j).getType() == Cell.Type.WALL) {
            stringBuilder.append(ANSI_WALL_COLOR).append(" ").append(ANSI_RESET_COLOR);
        } else {
            stringBuilder.append(" ");
        }
    }

    @Override
    public void print(Maze maze, List<Coordinate> path) {
        Objects.requireNonNull(maze);
        Objects.requireNonNull(path);


    }
}
