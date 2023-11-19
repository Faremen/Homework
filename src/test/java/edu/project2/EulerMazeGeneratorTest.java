package edu.project2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class EulerMazeGeneratorTest {

    @Test
    public void generate_InputSeedHeightWidth_ResultMaze() {
        // Given
        Cell[][] expectedGrid = {
            new Cell[] {
                new Cell(true, false),
                new Cell(false, false),
                new Cell(false, false),
                new Cell(false, false)
            },
            new Cell[] {
                new Cell(true, false),
                new Cell(true, false),
                new Cell(true, false),
                new Cell(false, false)
            },
            new Cell[] {
                new Cell(false, true),
                new Cell(true, true),
                new Cell(true, true),
                new Cell(false, true)
            }
        };

        MazePrinter printer = new ConsoleMazePrinter(2, 4, 1, 2);
        EulerMazeGenerator generator = new EulerMazeGenerator(1);
        int height = 3;
        int width = 4;

        // When
        Maze maze = generator.generate(height, width);
        printer.print(maze);
        Cell[][] actualGrid = maze.getGrid();


        // Then
        assertThat(actualGrid).isEqualTo(expectedGrid);
        assertThat(maze.getHeight()).isEqualTo(height);
        assertThat(maze.getWidth()).isEqualTo(width);
    }
}
