import Coordinates.TicTacToeCoordinates;

public class TicTacToeGame {

    private String playerName;
    private CellMatrix matrix;
    private TicTacToeReferee referee;

    public TicTacToeGame(String playerName) {
        this.playerName = playerName;
        this.matrix = new CellMatrix();
        this.referee = new TicTacToeReferee();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void playerMark(int x, int y) {
        matrix.playerMark(new TicTacToeCoordinates(x, y));
    }

    public void computerMark(int x, int y) {
        matrix.computerMark(new TicTacToeCoordinates(x, y));
    }

    public boolean thereIsAWinner() {
        TicTacToeReferee.RefereeVerdict v = referee.generateRefereeVerdict(matrix);
        return v.thereIsAWinner();
    }

    public boolean playerWon() {
        TicTacToeReferee.RefereeVerdict v = referee.generateRefereeVerdict(matrix);
        return v.playerIsTheWinner();
    }

    public boolean computerWon() {
        TicTacToeReferee.RefereeVerdict v = referee.generateRefereeVerdict(matrix);
        return v.computerIsTheWinner();
    }
}
