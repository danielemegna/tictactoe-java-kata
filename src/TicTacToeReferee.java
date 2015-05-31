import Coordinates.TicTacToeCoordinates;

public class TicTacToeReferee {

    private final CellMatrix matrix;

    public TicTacToeReferee(CellMatrix matrix) {
        this.matrix = matrix;
    }

    public boolean thereIsAWinner() {
        return playerIsTheWinner();
    }

    public boolean playerIsTheWinner() {
        return !matrix.isCellEmpty(new TicTacToeCoordinates(0,0));
    }

    public boolean computerIsTheWinner() {
        return false;
    }
}
