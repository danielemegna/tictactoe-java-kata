package component;

import TicTacToe.Cell.Matrix;
import TicTacToe.Cell.AlreadyMarkedCellAttemptException;
import TicTacToe.Cell.CellState;
import TicTacToe.Coordinates.Coordinates;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

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
                assertCellState(new Coordinates(x, y), CellState.Empty);
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
        assertCellState(c, CellState.PlayerMarked);
        c = new Coordinates(1, 1);
        matrix.playerMark(c);
        assertCellState(c, CellState.PlayerMarked);
    }

    @Test
    public void computerCanMarkEmptyCells() {
        Coordinates c;

        c = new Coordinates(0, 1);
        matrix.computerMark(c);
        assertCellState(c, CellState.ComputerMarked);
        c = new Coordinates(2, 1);
        matrix.computerMark(c);
        assertCellState(c, CellState.ComputerMarked);
    }

    @Test
    public void marksDoNotAffectOtherCells() {
        matrix.playerMark(new Coordinates(0, 1));
        matrix.playerMark(new Coordinates(2, 1));
        matrix.computerMark(new Coordinates(1, 0));
        matrix.computerMark(new Coordinates(2, 2));

        assertCellState(new Coordinates(0, 0), CellState.Empty);
        assertCellState(new Coordinates(1, 1), CellState.Empty);
        assertCellState(new Coordinates(1, 2), CellState.Empty);
    }

    private void assertCellState(Coordinates c, CellState expected) {
        CellState actual = matrix.getCellState(c);
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

    @Test
    public void matrixCanReturnAllTheEmptyCoordinates() {
        Set<Coordinates> emptyCoordinates;

        emptyCoordinates = matrix.getEmptyCoordinates();
        assertEquals(9, emptyCoordinates.size());
        assertTrue(
            "Empty coordinates set doesn't contains expected coordinates",
            emptyCoordinates.contains(new Coordinates(1, 1))
        );

        matrix.playerMark(new Coordinates(1, 1));
        emptyCoordinates = matrix.getEmptyCoordinates();
        assertEquals(8, emptyCoordinates.size());
        assertFalse(
            "Empty coordinates set contains unexpected coordinates",
            emptyCoordinates.contains(new Coordinates(1, 1))
        );

        matrix.computerMark(new Coordinates(0, 0));
        emptyCoordinates = matrix.getEmptyCoordinates();
        assertEquals(7, emptyCoordinates.size());
        assertFalse(
            "Empty coordinates set contains unexpected coordinates",
            emptyCoordinates.contains(new Coordinates(0, 0))
        );

        matrix.playerMark(new Coordinates(0, 1));
        matrix.computerMark(new Coordinates(0, 2));
        matrix.playerMark(new Coordinates(1, 0));
        matrix.computerMark(new Coordinates(1, 2));
        matrix.playerMark(new Coordinates(2, 0));
        matrix.computerMark(new Coordinates(2, 1));

        emptyCoordinates = matrix.getEmptyCoordinates();
        assertEquals(1, emptyCoordinates.size());
        assertTrue(
            "Empty coordinates set doesn't contains expected coordinates",
            emptyCoordinates.contains(new Coordinates(2, 2))
        );

        matrix.playerMark(new Coordinates(2, 2));

        emptyCoordinates = matrix.getEmptyCoordinates();
        assertEquals(0, emptyCoordinates.size());
    }

    @Test
    public void matrixIsClonable() {
        Coordinates makedBeforeClone = new Coordinates(0, 0);
        Coordinates markedAfterClone = new Coordinates(0, 1);
        Coordinates markedOnClone = new Coordinates(0, 2);

        matrix.playerMark(makedBeforeClone);
        Matrix clone = matrix.clone();
        matrix.playerMark(markedAfterClone);
        clone.playerMark(markedOnClone);

        assertEquals(CellState.PlayerMarked,    matrix.getCellState(makedBeforeClone));
        assertEquals(CellState.PlayerMarked,    clone.getCellState(makedBeforeClone));
        assertEquals(CellState.PlayerMarked,    matrix.getCellState(markedAfterClone));
        assertEquals(CellState.Empty,           clone.getCellState(markedAfterClone));
        assertEquals(CellState.Empty,           matrix.getCellState(markedOnClone));
        assertEquals(CellState.PlayerMarked,    clone.getCellState(markedOnClone));
    }

}
