import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by daniele on 22/05/15.
 */
public class CellTest {

    private Cell cell;

    @Before
    public void setUp() {
        cell = new Cell();
    }


    @Test
    public void aNewCellIsEmpty() {
        assertEquals(CellStateEnum.Empty, cell.getState());
    }

    @Test
    public void playerCanMarkAnEmptyCell() {
        cell.playerMark();
        assertEquals(CellStateEnum.PlayerMarked, cell.getState());
    }

    @Test
    public void markingTwiceSameCell_throwsAnAlreadyMarkedCellAttemptException()
    {
        cell.playerMark();
        try {
            cell.playerMark();
            fail("Expected AlreadyMarkedCellAttemptException has not been thrown!");
        } catch (AlreadyMarkedCellAttemptException ex) { }
    }

    @Test
    public void computerCanMarkAnEmptyCell() {
        cell.computerMark();
        assertEquals(CellStateEnum.ComputerMarked, cell.getState());
    }

}
