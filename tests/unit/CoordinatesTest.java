package unit;

import TicTacToe.Coordinates.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinatesTest {

    @Test
    public void trivialInstantiateAndGetCoordinates()
    {
        Coordinates c;

        c= new Coordinates(0, 1);
        assertEquals(0, c.getX());
        assertEquals(1, c.getY());

        c= new Coordinates(2, 0);
        assertEquals(2, c.getX());
        assertEquals(0, c.getY());
    }


    @Test
    public void createTrisCoordinatesWithWrongParams_throwsACoordinateOutOfBoundsException()
    {
        String failMessage = "Expected CoordinateOutOfBoundsException not catched";

        try { new Coordinates(0, 3); fail(failMessage); }
        catch(CoordinateOutOfBoundsException ex) {}
        try { new Coordinates(5, 1); fail(failMessage); }
        catch(CoordinateOutOfBoundsException ex) {}
        try { new Coordinates(42, 13); fail(failMessage); }
        catch(CoordinateOutOfBoundsException ex) {}
    }

}
