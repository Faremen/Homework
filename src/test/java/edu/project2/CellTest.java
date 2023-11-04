package edu.project2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CellTest {

    @Test
    public void constructor_CreateCellWithINullCellType_ResultNullPointerException() {
        assertThatThrownBy(() -> new Cell(null))
            .isInstanceOf(NullPointerException.class);
    }

    private static Stream<Arguments> getType_provideParameters() {
        return Stream.of(
            Arguments.of(Cell.Type.SPACE, Cell.Type.SPACE),
            Arguments.of(Cell.Type.WALL, Cell.Type.WALL)
        );
    }

    @ParameterizedTest
    @MethodSource("getType_provideParameters")
    public void getType_CreateCellWithInputCellType_ResultCellType(Cell.Type inputType, Cell.Type expected) {
        Cell cell = new Cell(inputType);
        var actual = cell.getType();

        assertThat(actual).isEqualTo(expected);
    }


    private static Stream<Arguments> setType_provideParameters() {
        return Stream.of(
            Arguments.of(new Cell(Cell.Type.WALL), Cell.Type.SPACE, new Cell(Cell.Type.SPACE)),
            Arguments.of(new Cell(Cell.Type.WALL), Cell.Type.WALL, new Cell(Cell.Type.WALL))
        );
    }

    @ParameterizedTest
    @MethodSource("setType_provideParameters")
    public void setType_CreateCellWithInputCellType_ResultCellType(Cell inputCell, Cell.Type setType, Cell expected) {
        inputCell.setType(setType);

        assertThat(inputCell).isEqualTo(expected);
    }

    @Test
    public void setType_SetNullCellType_ResultNullPointerException() {
        Cell cell = new Cell(Cell.Type.SPACE);

        assertThatThrownBy(() -> cell.setType(null))
            .isInstanceOf(NullPointerException.class);
    }
}
