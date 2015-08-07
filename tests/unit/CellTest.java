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
        assertEquals(CellState.Empty, cell.getState());
    }

    @Test
    public void playerMarksAnEmptyCell() {
        cell.playerMark();
        assertEquals(CellState.PlayerMarked, cell.getState());
    }

    @Test(expected = AlreadyMarkedCellAttemptException.class)
    public void playerMarksAnAlreadyMarkedCell_throwsAnException() {
        cell.playerMark();
        cell.playerMark();
    }
}
