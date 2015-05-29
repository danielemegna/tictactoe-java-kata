import Cell.AlreadyMarkedCellAttemptException;
import Cell.CellStateEnum;
import Coordinates.TrisCoordinates;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CellMatrixTest {

    private CellMatrix matrix;

    @Before
    public void setup() {
        this.matrix = new CellMatrix();
    }

    @Test
    public void inANewCellMatrix_CellsAreEmpty() {
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++) {
                TrisCoordinates c = new TrisCoordinates(x, y);
                assertCellState(c, CellStateEnum.Empty);
            }
    }

    @Test
    public void playerCanMarkEmptyCells() {
        TrisCoordinates c;

        c = new TrisCoordinates(0, 1);
        matrix.playerMark(c);
        assertCellState(c, CellStateEnum.PlayerMarked);
        c = new TrisCoordinates(1, 1);
        matrix.playerMark(c);
        assertCellState(c, CellStateEnum.PlayerMarked);
    }

    @Test
    public void computerCanMarkEmptyCells() {
        TrisCoordinates c;

        c = new TrisCoordinates(0, 1);
        matrix.computerMark(c);
        assertCellState(c, CellStateEnum.ComputerMarked);
        c = new TrisCoordinates(2, 1);
        matrix.computerMark(c);
        assertCellState(c, CellStateEnum.ComputerMarked);
    }

    @Test
    public void marksDoNotAffectOtherCells() {
        matrix.playerMark(new TrisCoordinates(0, 1));
        matrix.playerMark(new TrisCoordinates(2, 1));
        matrix.computerMark(new TrisCoordinates(1, 0));
        matrix.computerMark(new TrisCoordinates(2, 2));

        assertCellState(new TrisCoordinates(0, 0), CellStateEnum.Empty);
        assertCellState(new TrisCoordinates(1, 1), CellStateEnum.Empty);
        assertCellState(new TrisCoordinates(1, 2), CellStateEnum.Empty);
    }

    private void assertCellState(TrisCoordinates c, CellStateEnum expected) {
        CellStateEnum actual = matrix.getCellState(c);
        assertEquals(
                "Fail asserting cell state at coordinates [" + c + "] ." +
                "Actual [" + actual + "], expected [" + expected + "]",
                expected, actual
        );
    }

    @Test
    public void markingTwiceTheSameCell_throwsAnAlreadyMarkedCellAttemptException() {
        String failMessage = "Expected Cell.AlreadyMarkedCellAttemptException has not been thrown!";
        TrisCoordinates[] toBeTested = new TrisCoordinates[] {
            new TrisCoordinates(0, 0),
            new TrisCoordinates(1, 1)
        };

        matrix.playerMark(toBeTested[0]);
        matrix.computerMark(toBeTested[1]);

        for(TrisCoordinates c : toBeTested) {
            try {
                matrix.playerMark(c);
                fail(failMessage);
            } catch (AlreadyMarkedCellAttemptException ex) {}
            try {
                matrix.computerMark(c);
                fail(failMessage);
            } catch (AlreadyMarkedCellAttemptException ex) {}
        }
    }

}
