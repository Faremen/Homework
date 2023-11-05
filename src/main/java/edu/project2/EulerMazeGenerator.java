package edu.project2;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class EulerMazeGenerator implements MazeGenerator {

    int[] rowMarkers;

    private final int voidMark = -1;

    private int counterMarkers = 0;

    private final Random random;

    public EulerMazeGenerator(long seed) {
        this.random = new Random(seed);
    }

    public EulerMazeGenerator() {
        this.random = new Random();
    }

    @Override
    public Maze generate(int height, int width) {
        rowMarkers = new int[width];
        Arrays.fill(rowMarkers, voidMark);

        Maze maze = new Maze(height, width, new Cell(false, true));

        for (int i = 0; i < height - 1; i++) {
            fillVoids();
            createWallsBetweenIdentical(width, maze, i);
            unionMarkers(width, maze, i);
            oneRemoveBottomWalls(maze, i);
            randomRemoveBottomWall(width, maze, i);
        }

        fillVoids();
        createWallsBetweenIdentical(width, maze, height - 1);
        removeWallsBetweenDifferent(height, width, maze);

        return maze;
    }

    private void removeWallsBetweenDifferent(int height, int width, Maze maze) {
        for (int i = 0; i < width - 1; i++) {
            if (rowMarkers[i] != rowMarkers[i + 1]) {
                maze.getCell(height - 1, i).setRightWall(false);
            }
        }
    }

    private void randomRemoveBottomWall(int width, Maze maze, int i) {
        for (int j = 0; j < width; j++) {
            if (random.nextBoolean()) {
                maze.getCell(i, j).setBottomWall(false);
            }
            if (maze.getCell(i, j).isBottomWall()) {
                rowMarkers[j] = voidMark;
            }
        }
    }

    private void oneRemoveBottomWalls(Maze maze, int i) {
        Set<Integer> uniNumber = Arrays.stream(rowMarkers).boxed().collect(Collectors.toSet());
        for (Integer j : uniNumber) {
            Range range = findRangeForMark(rowMarkers, j);

            if (range.start != -1 && range.end != -1) {
                maze.getCell(i, random.nextInt(range.start, range.end + 1))
                    .setBottomWall(false);
            }
        }
    }

    private void unionMarkers(int width, Maze maze, int i) {
        for (int j = 0; j < width - 1; j++) {
            if (random.nextBoolean()) {
                maze.getCell(i, j).setRightWall(true);
            } else {
                rowMarkers[j + 1] = rowMarkers[j];
            }
        }
    }

    private void createWallsBetweenIdentical(int width, Maze maze, int i) {
        for (int j = 0; j < width - 1; j++) {
            if (rowMarkers[j] == rowMarkers[j + 1]) {
                maze.getCell(i, j).setRightWall(true);
            }
        }
    }

    private void fillVoids() {
        for (int i = 0; i < rowMarkers.length; i++) {
            if (rowMarkers[i] == voidMark) {
                rowMarkers[i] = counterMarkers++;
            }
        }
    }

    private Range findRangeForMark(int[] markers, int search) {
        boolean firstFinding = false;
        int start = -1;
        int end = -1;

        for (int i = 0; i < markers.length; i++) {
            if (markers[i] == search && !firstFinding) {
                start = i;
                end = i;
                firstFinding = true;
            }

            if (markers[i] != search && start != -1) {
                end = i - 1;
                break;
            }
        }

        return new Range(start, end);
    }

    private record Range(int start, int end) {
    }
}
