package TicTacToe.Coordinates;

public class Coordinates {

    private static final int MAX_COORDINATE_VALUE = 2;

    private int x;
    private int y;

    public Coordinates(int x, int y) {
        if(x < 0 || x > MAX_COORDINATE_VALUE || y < 0 || y > MAX_COORDINATE_VALUE)
            throw new CoordinateOutOfBoundsException();

        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Coordinates))
            return super.equals(o);

        Coordinates that = (Coordinates)o;
        return this.x == that.x && this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(
            String.valueOf(this.x) + String.valueOf(this.y)
        );
    }

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }
}
