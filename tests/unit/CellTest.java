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
        assertEquals(CellMarkSign.Empty, cell.getState());
    }

    @Test
    public void markAnEmptyCellWithNewState() {
        cell.markWithState(CellMarkSign.CrossMarked);
        assertEquals(CellMarkSign.CrossMarked, cell.getState());
    }

    @Test(expected = AlreadyMarkedCellAttemptException.class)
    public void playerMarksAnAlreadyMarkedCell_throwsAnException() {
        cell.markWithState(CellMarkSign.CircleMarked);
        cell.markWithState(CellMarkSign.CrossMarked);
    }
}
