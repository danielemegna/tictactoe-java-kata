import Coordinates.CoordinateOutOfBoundsException;
import Coordinates.TrisCoordinates;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TrisCoordinatesTest {

    @Test
    public void trivialInstantiateAndGetCoordinates()
    {
        TrisCoordinates c;

        c= new TrisCoordinates(0, 1);
        assertEquals(0, c.getX());
        assertEquals(1, c.getY());

        c= new TrisCoordinates(2, 0);
        assertEquals(2, c.getX());
        assertEquals(0, c.getY());
    }


    @Test
    public void createTrisCoordinatesWithWrongParams_throwsACoordinateOutOfBoundsException()
    {
        String failMessage = "Expected CoordinateOutOfBoundsException not catched";

        try { new TrisCoordinates(0, 3); fail(failMessage); }
        catch(CoordinateOutOfBoundsException ex) {}
        try { new TrisCoordinates(5, 1); fail(failMessage); }
        catch(CoordinateOutOfBoundsException ex) {}
        try { new TrisCoordinates(42, 13); fail(failMessage); }
        catch(CoordinateOutOfBoundsException ex) {}
    }

}
