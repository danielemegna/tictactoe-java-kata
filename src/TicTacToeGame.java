/**
 * Created by daniele on 21/05/15.
 */
public class TicTacToeGame {

    private String playerName;
    private CellMatrix matrix;

    public TicTacToeGame(String playerName) {
        this.playerName = playerName;
        this.matrix = new CellMatrix();
    }

    public CellStateEnum getCellState(int x, int y) {
        return matrix.getCellState(new Coordinates(x, y));
    }

    public void playerMark(int x, int y) {
        matrix.playerMark(new Coordinates(x, y));
    }

    public void computerMark(int x, int y) {
        matrix.computerMark(new Coordinates(x, y));
    }

    public boolean isFull() {
        return false;
    }

    public boolean thereIsAWinner() {
        return playerWon() || computerWon();
    }

    public boolean playerWon() {
        return
        (
            isCellPlayerMarked(new Coordinates(0,0)) &&
            isCellPlayerMarked(new Coordinates(1,0)) &&
            isCellPlayerMarked(new Coordinates(2,0))
        ) ||
        (
            isCellPlayerMarked(new Coordinates(0,0)) &&
            isCellPlayerMarked(new Coordinates(0,1)) &&
            isCellPlayerMarked(new Coordinates(0,2))
        ) ||
        (
            isCellPlayerMarked(new Coordinates(0,2)) &&
            isCellPlayerMarked(new Coordinates(1,2)) &&
            isCellPlayerMarked(new Coordinates(2,2))
        );
    }

    public boolean computerWon() {
        return
        (
            isCellComputerMarked(new Coordinates(0,1)) &&
            isCellComputerMarked(new Coordinates(1,1)) &&
            isCellComputerMarked(new Coordinates(2,1))
        );
    }

    public void reset() {
        this.matrix = new CellMatrix();
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isCellEmpty(int x, int y) {
        return matrix.isCellEmpty(new Coordinates(x, y));
    }

    private boolean isCellPlayerMarked(Coordinates c) {
        return getCellState(c.getX(), c.getY()) == CellStateEnum.PlayerMarked;
    }

    private boolean isCellComputerMarked(Coordinates c) {
        return getCellState(c.getX(), c.getY()) == CellStateEnum.ComputerMarked;
    }
}
