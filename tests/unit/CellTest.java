package unit;

import TicTacToe.Cell.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    private Cell cell;

    @Before
    public void setUp() {
        cell = new Cell();
    }

    @Test
    public void aNewCellIsEmpty() {
        assertTrue(cell.isEmpty());
    }

    @Test
    public void markAnEmptyCellWithNewState() {
        cell.mark(CellMarkSign.Cross);
        assertEquals(CellMarkSign.Cross, cell.getCurrentSign());
    }

    @Test(expected = AlreadyMarkedCellAttemptException.class)
    public void playerMarksAnAlreadyMarkedCell_throwsAnException() {
        cell.mark(CellMarkSign.Circle);
        cell.mark(CellMarkSign.Circle);
    }
}
