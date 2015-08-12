package unit;

import TicTacToe.Coordinates.*;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CoordinatesTest {

    @Test
    public void trivialInstantiateAndGetCoordinates()
    {
        Coordinates c = new Coordinates(0, 1);
        assertEquals(0, c.getX());
        assertEquals(1, c.getY());
    }


    @Test(expected = CoordinateOutOfBoundsException.class)
    public void createCoordinatesWithWrongParams_throwsAnException() {
        new Coordinates(5, 1);
    }

    @Test
    public void coordinatesToString() {
        Coordinates c = new Coordinates(2, 1);
        assertEquals("[2, 1]", c.toString());
    }

    @Test
    public void coordinatesEquality() {
        Coordinates zeroZero                = new Coordinates(0, 0);
        Coordinates anotherZeroZero         = new Coordinates(0, 0);
        Coordinates differentCoordinates    = new Coordinates(1, 0);

        assertEquals(zeroZero, anotherZeroZero);
        assertNotEquals(zeroZero, differentCoordinates);
    }

    @Test
    public void coordinatesHashCodesEquality() {
        Coordinates zeroZero                = new Coordinates(0, 0);
        Coordinates anotherZeroZero         = new Coordinates(0, 0);
        Coordinates differentCoordinates    = new Coordinates(1, 0);

        assertEquals(zeroZero.hashCode(), anotherZeroZero.hashCode());
        assertNotEquals(zeroZero.hashCode(), differentCoordinates.hashCode());
    }

    @Test
    public void coordinatesInequalityWithDifferentClasses() {
        Coordinates coordinates = new Coordinates(0, 0);
        BigDecimal bigDecimal = BigDecimal.TEN;

        assertNotEquals(coordinates, bigDecimal);
        assertNotEquals(coordinates.hashCode(), bigDecimal.hashCode());
    }
}
