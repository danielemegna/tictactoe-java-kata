package it;

import TicTacToe.Cell.Matrix;
import TicTacToe.Cell.AlreadyMarkedCellAttemptException;
import TicTacToe.Cell.CellStateEnum;
import TicTacToe.Coordinates.Coordinates;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    private Matrix cellMatrix;

    @Before
    public void setup() {
        this.cellMatrix = new Matrix();
    }

    @Test
    public void inANewCellMatrix_CellsAreEmpty() {
        for(int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Coordinates c = new Coordinates(x, y);
                assertCellState(c, CellStateEnum.Empty);
            }
        }
    }

    @Test
    public void playerCanMarkEmptyCells() {
        Coordinates c;

        c = new Coordinates(0, 1);
        cellMatrix.playerMark(c);
        assertCellState(c, CellStateEnum.PlayerMarked);
        c = new Coordinates(1, 1);
        cellMatrix.playerMark(c);
        assertCellState(c, CellStateEnum.PlayerMarked);
    }

    @Test
    public void computerCanMarkEmptyCells() {
        Coordinates c;

        c = new Coordinates(0, 1);
        cellMatrix.computerMark(c);
        assertCellState(c, CellStateEnum.ComputerMarked);
        c = new Coordinates(2, 1);
        cellMatrix.computerMark(c);
        assertCellState(c, CellStateEnum.ComputerMarked);
    }

    @Test
    public void marksDoNotAffectOtherCells() {
        cellMatrix.playerMark(new Coordinates(0, 1));
        cellMatrix.playerMark(new Coordinates(2, 1));
        cellMatrix.computerMark(new Coordinates(1, 0));
        cellMatrix.computerMark(new Coordinates(2, 2));

        assertCellState(new Coordinates(0, 0), CellStateEnum.Empty);
        assertCellState(new Coordinates(1, 1), CellStateEnum.Empty);
        assertCellState(new Coordinates(1, 2), CellStateEnum.Empty);
    }

    private void assertCellState(Coordinates c, CellStateEnum expected) {
        CellStateEnum actual = cellMatrix.getCellState(c);
        assertEquals(
            "Fail asserting cell state at coordinates [" + c + "] ." +
            "Actual [" + actual + "], expected [" + expected + "]",
            expected, actual
        );
    }

    @Test
    public void markingTwiceTheSameCell_throwsAnAlreadyMarkedCellAttemptException() {
        String failMessage = "Expected AlreadyMarkedCellAttemptException has not been thrown!";
        Coordinates[] toBeTested = new Coordinates[] {
            new Coordinates(0, 0),
            new Coordinates(1, 1)
        };

        cellMatrix.playerMark(toBeTested[0]);
        cellMatrix.computerMark(toBeTested[1]);

        for(Coordinates c : toBeTested) {
            try {
                cellMatrix.playerMark(c);
                fail(failMessage);
            } catch (AlreadyMarkedCellAttemptException ex) {}
            try {
                cellMatrix.computerMark(c);
                fail(failMessage);
            } catch (AlreadyMarkedCellAttemptException ex) {}
        }
    }

}
