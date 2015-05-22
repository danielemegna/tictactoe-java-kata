import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by daniele on 22/05/15.
 */
public class CellTest {

    @Test
    public void aNewCellIsEmpty() {
        Cell c = new Cell();
        assertEquals(CellStateEnum.Empty, c.getState());
    }



}
