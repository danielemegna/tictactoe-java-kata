package Coordinates;

public class TicTacToeCoordinates extends Coordinates {

    public TicTacToeCoordinates(int x, int y) {
        super(x,y);

        if(x > 2 || y > 2)
            throw new CoordinateOutOfBoundsException();
    }

}
