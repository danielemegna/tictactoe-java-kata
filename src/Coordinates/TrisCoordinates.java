package Coordinates;

public class TrisCoordinates extends Coordinates {
    public TrisCoordinates(int x, int y) {
        super(x,y);

        if(x > 2 || y > 2)
            throw new CoordinateOutOfBoundsException();
    }
}
