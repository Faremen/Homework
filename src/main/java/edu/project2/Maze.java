package edu.project2;

import java.util.Objects;

public class Maze {

    private static final int MIN_HEIGHT = 3;
    private static final int MIN_WIDTH = 3;
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width) {
        this(height, width, new Cell(true, true));
    }

    public Maze(int height, int width, Cell cell) {
        if (height < MIN_HEIGHT || width < MIN_WIDTH) {
            throw new IllegalArgumentException(String.format("height must be >= %d and width must be >= %d",
                MIN_HEIGHT, MIN_WIDTH));
        }

        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
        fillGrid(cell);
    }

    public void setCell(int row, int colum, Cell cell) {
        Objects.requireNonNull(cell);

        grid[row][colum].setRightWall(cell.isRightWall());
        grid[row][colum].setBottomWall(cell.isBottomWall());
    }

    public Cell getCell(int row, int colum) {
        return grid[row][colum];
    }

    public Cell getCell(Coordinate coordinate) {
        return grid[coordinate.row()][coordinate.colum()];
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
                    deapCopy[i][j] = new Cell(
                        grid[i][j].isRightWall(),
                        grid[i][j].isBottomWall()
                    );
                } else {
                    deapCopy[i][j] = null;
                }
            }
        }

        return deapCopy;
    }

    private void fillGrid(Cell cell) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(cell.isRightWall(), cell.isBottomWall());
            }
        }
    }
}
