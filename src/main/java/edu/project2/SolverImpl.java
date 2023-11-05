package edu.project2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SolverImpl implements Solver {

    private Map<Integer, Set<Coordinate>> fillingMap;

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        fillingMap = new HashMap<>();
        int currentCount = 0;

        fillingMap.put(currentCount, Set.of(start));
        currentCount++;

        while (!isCoordinateContains(end)) {
            Set<Coordinate> tempSet = new HashSet<>();

            fillTempSet(maze, currentCount, tempSet);

            fillingMap.put(currentCount, tempSet);
            currentCount++;
        }

        return getPath(maze, end, --currentCount);
    }

    private List<Coordinate> getPath(Maze maze, Coordinate end, int currentCount) {
        int tempCount = currentCount;

        List<Coordinate> result = new ArrayList<>();
        Coordinate currentCoordinate = end;
        result.add(end);

        Set<Coordinate> tempSet;
        while ((tempSet = fillingMap.get(--tempCount)) != null) {
            Coordinate temp = getBackCoordinate(maze, currentCoordinate, tempSet);
            currentCoordinate = temp;
            result.add(temp);
        }
        return result;
    }

    private void fillTempSet(Maze maze, int currentCount, Set<Coordinate> tempSet) {
        Set<Coordinate> coordinateSet = fillingMap.get(currentCount - 1);

        for (Coordinate coordinate : coordinateSet) {
            if (!isCoordinateContains(getRight(coordinate))
                && isEnterRight(maze, coordinate)) {
                tempSet.add(getRight(coordinate));
            }
            if (!isCoordinateContains(getBottom(coordinate))
                && isEnterBottom(maze, coordinate)) {
                tempSet.add(getBottom(coordinate));
            }
            if (!isCoordinateContains(getLeft(coordinate))
                && isEnterLeft(maze, coordinate)) {
                tempSet.add(getLeft(coordinate));
            }
            if (!isCoordinateContains(getTop(coordinate))
                && isEnterTop(maze, coordinate)) {
                tempSet.add(getTop(coordinate));
            }
        }
    }

    private Coordinate getBackCoordinate(Maze maze, Coordinate coordinate, Set<Coordinate> findingSet) {
        Coordinate result = null;

        if (findingSet.contains(getRight(coordinate))
            && isEnterRight(maze, coordinate)) {
            result = getRight(coordinate);
        } else if (findingSet.contains(getBottom(coordinate))
            && isEnterBottom(maze, coordinate)) {
            result = getBottom(coordinate);
        } else if (findingSet.contains(getLeft(coordinate))
            && isEnterLeft(maze, coordinate)) {
            result = getLeft(coordinate);
        } else if (findingSet.contains(getTop(coordinate))
            && isEnterTop(maze, coordinate)) {
            result = getTop(coordinate);
        }

        return result;
    }

    private boolean isEnterTop(Maze maze, Coordinate cord) {
        return cord.row() != 0
            && !maze.getCell(getTop(cord)).isBottomWall();
    }

    private boolean isEnterLeft(Maze maze, Coordinate cord) {
        return cord.colum() != 0
            && !maze.getCell(getLeft(cord)).isRightWall();
    }

    private boolean isEnterBottom(Maze maze, Coordinate cord) {
        return cord.row() != maze.getHeight() - 1
            && !maze.getCell(cord).isBottomWall();
    }

    private boolean isEnterRight(Maze maze, Coordinate cord) {
        return cord.colum() != maze.getWidth() - 1
            && !maze.getCell(cord).isRightWall();
    }

    private Coordinate getTop(Coordinate coordinate) {
        return new Coordinate(coordinate.row() - 1, coordinate.colum());
    }

    private Coordinate getLeft(Coordinate coordinate) {
        return new Coordinate(coordinate.row(), coordinate.colum() - 1);
    }

    private Coordinate getBottom(Coordinate coordinate) {
        return new Coordinate(coordinate.row() + 1, coordinate.colum());
    }

    private Coordinate getRight(Coordinate coordinate) {
        return new Coordinate(coordinate.row(), coordinate.colum() + 1);
    }

    private boolean isCoordinateContains(Coordinate coordinate) {
        return fillingMap.entrySet()
            .stream()
            .anyMatch((e) -> e.getValue().contains(coordinate));
    }
}
