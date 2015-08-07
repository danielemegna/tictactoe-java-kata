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
    public void playerCanMarkAnEmptyCell() {
        cell.playerMark();
        assertEquals(CellState.PlayerMarked, cell.getState());
    }

    @Test
    public void playerMarkTwiceSameCell_throwsAnAlreadyMarkedCellAttemptException() {
        cell.playerMark();
        try {
            cell.playerMark();
            fail("Expected AlreadyMarkedCellAttemptException has not been thrown!");
        } catch (AlreadyMarkedCellAttemptException ex) { }
    }

    @Test
    public void computerCanMarkAnEmptyCell() {
        cell.computerMark();
        assertEquals(CellState.ComputerMarked, cell.getState());
    }

    @Test
    public void computerMarkTwiceSameCell_throwsAnAlreadyMarkedCellAttemptException() {
        cell.computerMark();
        try {
            cell.computerMark();
            fail("Expected AlreadyMarkedCellAttemptException has not been thrown!");
        } catch (AlreadyMarkedCellAttemptException ex) { }
    }

}
