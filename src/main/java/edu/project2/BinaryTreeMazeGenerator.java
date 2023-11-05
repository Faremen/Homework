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
        Maze maze = new Maze(height, width);

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (i == 0) {
                    maze.getCell(i, j).setRightWall(false);
                } else if (j == 0) {
                    maze.getCell(i - 1, j).setBottomWall(false);
                } else {
                    if (random.nextBoolean()) {
                        maze.getCell(i - 1, j).setBottomWall(false);
                    } else {
                        maze.getCell(i, j - 1).setRightWall(false);
                    }
                }
            }
        }

        return maze;
    }
}
