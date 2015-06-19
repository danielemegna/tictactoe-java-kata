package component;

import TicTacToe.Cell.Matrix;
import TicTacToe.Cell.AlreadyMarkedCellAttemptException;
import TicTacToe.Cell.CellStateEnum;
import TicTacToe.Coordinates.Coordinates;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    private Matrix matrix;

    @Before
    public void setup() {
        this.matrix = new Matrix();
    }

    @Test
    public void inANewCellMatrix_CellsAreEmpty() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                assertCellState(new Coordinates(x, y), CellStateEnum.Empty);
            }
        }
    }

    @Test
    public void aNewMatrixIsNotFull() {
        assertFalse(matrix.isFull());
    }

    @Test
    public void playerCanMarkEmptyCells() {
        Coordinates c;

        c = new Coordinates(0, 1);
        matrix.playerMark(c);
        assertCellState(c, CellStateEnum.PlayerMarked);
        c = new Coordinates(1, 1);
        matrix.playerMark(c);
        assertCellState(c, CellStateEnum.PlayerMarked);
    }

    @Test
    public void computerCanMarkEmptyCells() {
        Coordinates c;

        c = new Coordinates(0, 1);
        matrix.computerMark(c);
        assertCellState(c, CellStateEnum.ComputerMarked);
        c = new Coordinates(2, 1);
        matrix.computerMark(c);
        assertCellState(c, CellStateEnum.ComputerMarked);
    }

    @Test
    public void marksDoNotAffectOtherCells() {
        matrix.playerMark(new Coordinates(0, 1));
        matrix.playerMark(new Coordinates(2, 1));
        matrix.computerMark(new Coordinates(1, 0));
        matrix.computerMark(new Coordinates(2, 2));

        assertCellState(new Coordinates(0, 0), CellStateEnum.Empty);
        assertCellState(new Coordinates(1, 1), CellStateEnum.Empty);
        assertCellState(new Coordinates(1, 2), CellStateEnum.Empty);
    }

    private void assertCellState(Coordinates c, CellStateEnum expected) {
        CellStateEnum actual = matrix.getCellState(c);
        assertEquals(
                "Fail asserting cell state at coordinates [" + c + "] ." +
                        "Actual [" + actual + "], expected [" + expected + "]",
                expected, actual
        );
    }

    @Test
    public void playersCannotMarkCellTwice() {
        String failMessage = "Expected AlreadyMarkedCellAttemptException has not been thrown!";

        Coordinates playerMarked = new Coordinates(0, 0);
        Coordinates computerMarked = new Coordinates(1, 1);

        matrix.playerMark(playerMarked);
        matrix.computerMark(computerMarked);

        try {
            matrix.playerMark(playerMarked);
            fail(failMessage);
        } catch (AlreadyMarkedCellAttemptException ex) {}
        try {
            matrix.computerMark(computerMarked);
            fail(failMessage);
        } catch (AlreadyMarkedCellAttemptException ex) {}
    }

    @Test
    public void playersCannotMarkCellsAlreadyMarkedByOthers() {
        String failMessage = "Expected AlreadyMarkedCellAttemptException has not been thrown!";

        Coordinates playerMarked = new Coordinates(0, 0);
        Coordinates computerMarked = new Coordinates(1, 1);

        matrix.playerMark(playerMarked);
        matrix.computerMark(computerMarked);

        try {
            matrix.playerMark(computerMarked);
            fail(failMessage);
        } catch (AlreadyMarkedCellAttemptException ex) {}
        try {
            matrix.computerMark(playerMarked);
            fail(failMessage);
        } catch (AlreadyMarkedCellAttemptException ex) {}
    }

    @Test
    public void markingEveryCell_matrixIsFull() {
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                matrix.playerMark(new Coordinates(x, y));

        assertTrue(matrix.isFull());
    }

}
