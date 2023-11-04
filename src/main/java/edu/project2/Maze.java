package edu.project2;

import java.util.Arrays;
import java.util.Objects;

public class Maze {

    private static final int MIN_HEIGHT = 5;
    private static final int MIN_WIDTH = 5;
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width, Cell.Type fillingCellType) {
        Objects.requireNonNull(fillingCellType);

        if (height < MIN_HEIGHT || width < MIN_WIDTH) {
            throw new IllegalArgumentException(String.format("height must be >= %d and width must be >= %d",
                MIN_HEIGHT, MIN_WIDTH));
        }

        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
        fillGrid(fillingCellType);
    }

    public void setCellType(int row, int colum, Cell.Type type) {
        Objects.requireNonNull(type);

        grid[row][colum].setType(type);
    }

    public Cell getCell(int row, int colum) {
        return grid[row][colum] == null ? null : new Cell(grid[row][colum].getType());
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell[][] getGrid() {
        return getDeapCopyGrid(this.grid);
    }

    private Cell[][] getDeapCopyGrid(Cell[][] grid) {
        Cell[][] deapCopy = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] != null) {
                    deapCopy[i][j] = new Cell(grid[i][j].getType());
                } else {
                    deapCopy[i][j] = null;
                }
            }
        }

        return deapCopy;
    }

    private void fillGrid(Cell.Type cellType) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(cellType);
            }
        }
    }
}
