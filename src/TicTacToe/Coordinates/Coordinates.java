package TicTacToe.Coordinates;

public class Coordinates {

    private int x;
    private int y;

    public Coordinates(int x, int y) {
        if(x > 2 || y > 2)
            throw new CoordinateOutOfBoundsException();

        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

}
