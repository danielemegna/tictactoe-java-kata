import Coordinates.CoordinateOutOfBoundsException;
import Coordinates.TicTacToeCoordinates;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TicTacToeCoordinatesTest {

    @Test
    public void trivialInstantiateAndGetCoordinates()
    {
        TicTacToeCoordinates c;

        c= new TicTacToeCoordinates(0, 1);
        assertEquals(0, c.getX());
        assertEquals(1, c.getY());

        c= new TicTacToeCoordinates(2, 0);
        assertEquals(2, c.getX());
        assertEquals(0, c.getY());
    }


    @Test
    public void createTrisCoordinatesWithWrongParams_throwsACoordinateOutOfBoundsException()
    {
        String failMessage = "Expected CoordinateOutOfBoundsException not catched";

        try { new TicTacToeCoordinates(0, 3); fail(failMessage); }
        catch(CoordinateOutOfBoundsException ex) {}
        try { new TicTacToeCoordinates(5, 1); fail(failMessage); }
        catch(CoordinateOutOfBoundsException ex) {}
        try { new TicTacToeCoordinates(42, 13); fail(failMessage); }
        catch(CoordinateOutOfBoundsException ex) {}
    }

}
