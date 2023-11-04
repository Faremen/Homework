package edu.project2;

import java.util.Random;

public class BinaryTreeMazeGenerator implements MazeGenerator {

    private final Random random;

    public BinaryTreeMazeGenerator(long seed) {
        this.random = new Random(seed);
    }

    public BinaryTreeMazeGenerator() {
        this.random = new Random();
    }

    @Override
    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width, Cell.Type.WALL);
//        fillBorder(maze);

        maze.setCellType(1, 1, Cell.Type.SPACE);

        for (int i = 1; i < maze.getHeight() - 1; i += 2) {
            for (int j = 1; j < maze.getWidth() - 1; j += 2) {
                if (maze.getCell(i - 1, j).getType() == Cell.Type.WALL) {
                    maze.setCellType(i, j + 1, Cell.Type.SPACE);
                }
            }
        }

        return maze;
    }

    private void fillBorder(Maze maze) {
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if ((i == 0) || (i == maze.getHeight() - 1) || (j == 0) || (j == maze.getWidth() - 1)) {
                    maze.setCellType(i, j, Cell.Type.WALL);
                }
            }
        }
    }
}
