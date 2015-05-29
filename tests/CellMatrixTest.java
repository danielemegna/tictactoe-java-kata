import TicTacToeCell.AlreadyMarkedCellAttemptException;
import TicTacToeCell.CellStateEnum;
import Coordinates.TicTacToeCoordinates;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CellMatrixTest {

    private CellMatrix cellMatrix;

    @Before
    public void setup() {
        this.cellMatrix = new CellMatrix();
    }

    @Test
    public void inANewCellMatrix_CellsAreEmpty() {
        for(int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                TicTacToeCoordinates c = new TicTacToeCoordinates(x, y);
                assertCellState(c, CellStateEnum.Empty);
            }
        }
    }

    @Test
    public void playerCanMarkEmptyCells() {
        TicTacToeCoordinates c;

        c = new TicTacToeCoordinates(0, 1);
        cellMatrix.playerMark(c);
        assertCellState(c, CellStateEnum.PlayerMarked);
        c = new TicTacToeCoordinates(1, 1);
        cellMatrix.playerMark(c);
        assertCellState(c, CellStateEnum.PlayerMarked);
    }

    @Test
    public void computerCanMarkEmptyCells() {
        TicTacToeCoordinates c;

        c = new TicTacToeCoordinates(0, 1);
        cellMatrix.computerMark(c);
        assertCellState(c, CellStateEnum.ComputerMarked);
        c = new TicTacToeCoordinates(2, 1);
        cellMatrix.computerMark(c);
        assertCellState(c, CellStateEnum.ComputerMarked);
    }

    @Test
    public void marksDoNotAffectOtherCells() {
        cellMatrix.playerMark(new TicTacToeCoordinates(0, 1));
        cellMatrix.playerMark(new TicTacToeCoordinates(2, 1));
        cellMatrix.computerMark(new TicTacToeCoordinates(1, 0));
        cellMatrix.computerMark(new TicTacToeCoordinates(2, 2));

        assertCellState(new TicTacToeCoordinates(0, 0), CellStateEnum.Empty);
        assertCellState(new TicTacToeCoordinates(1, 1), CellStateEnum.Empty);
        assertCellState(new TicTacToeCoordinates(1, 2), CellStateEnum.Empty);
    }

    private void assertCellState(TicTacToeCoordinates c, CellStateEnum expected) {
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
        TicTacToeCoordinates[] toBeTested = new TicTacToeCoordinates[] {
            new TicTacToeCoordinates(0, 0),
            new TicTacToeCoordinates(1, 1)
        };

        cellMatrix.playerMark(toBeTested[0]);
        cellMatrix.computerMark(toBeTested[1]);

        for(TicTacToeCoordinates c : toBeTested) {
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
