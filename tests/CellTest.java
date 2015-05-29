import TicTacToeCell.Cell;
import TicTacToeCell.CellStateEnum;
import TicTacToeCell.AlreadyMarkedCellAttemptException;
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
        assertEquals(CellStateEnum.Empty, cell.getState());
        assertTrue(cell.isEmpty());
    }

    @Test
    public void playerCanMarkAnEmptyCell() {
        cell.playerMark();
        assertEquals(CellStateEnum.PlayerMarked, cell.getState());
        assertFalse(cell.isEmpty());
    }

    @Test
    public void playerMarkTwiceSameCell_throwsAnAlreadyMarkedCellAttemptException() {
        cell.playerMark();
        try {
            cell.playerMark();
            fail("Expected TicTacToeCell.AlreadyMarkedCellAttemptException has not been thrown!");
        } catch (AlreadyMarkedCellAttemptException ex) { }
    }

    @Test
    public void computerCanMarkAnEmptyCell() {
        cell.computerMark();
        assertEquals(CellStateEnum.ComputerMarked, cell.getState());
    }

    @Test
    public void computerMarkTwiceSameCell_throwsAnAlreadyMarkedCellAttemptException() {
        cell.computerMark();
        try {
            cell.computerMark();
            fail("Expected TicTacToeCell.AlreadyMarkedCellAttemptException has not been thrown!");
        } catch (AlreadyMarkedCellAttemptException ex) { }
    }

}
