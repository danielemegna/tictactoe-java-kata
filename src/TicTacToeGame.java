import Cell.CellStateEnum;
import Coordinates.TrisCoordinates;

public class TicTacToeGame {

    private String playerName;
    private CellMatrix matrix;

    public TicTacToeGame(String playerName) {
        this.playerName = playerName;
        this.matrix = new CellMatrix();
    }

    public CellStateEnum getCellState(int x, int y) {
        return matrix.getCellState(new TrisCoordinates(x, y));
    }

    public void playerMark(int x, int y) {
        matrix.playerMark(new TrisCoordinates(x, y));
    }

    public void computerMark(int x, int y) {
        matrix.computerMark(new TrisCoordinates(x, y));
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
            isCellPlayerMarked(new TrisCoordinates(0,0)) &&
            isCellPlayerMarked(new TrisCoordinates(1,0)) &&
            isCellPlayerMarked(new TrisCoordinates(2,0))
        ) ||
        (
            isCellPlayerMarked(new TrisCoordinates(0,0)) &&
            isCellPlayerMarked(new TrisCoordinates(0,1)) &&
            isCellPlayerMarked(new TrisCoordinates(0,2))
        ) ||
        (
            isCellPlayerMarked(new TrisCoordinates(0,2)) &&
            isCellPlayerMarked(new TrisCoordinates(1,2)) &&
            isCellPlayerMarked(new TrisCoordinates(2,2))
        );
    }

    public boolean computerWon() {
        return
        (
            isCellComputerMarked(new TrisCoordinates(0,1)) &&
            isCellComputerMarked(new TrisCoordinates(1,1)) &&
            isCellComputerMarked(new TrisCoordinates(2,1))
        );
    }

    public void reset() {
        this.matrix = new CellMatrix();
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isCellEmpty(int x, int y) {
        return matrix.isCellEmpty(new TrisCoordinates(x, y));
    }

    private boolean isCellPlayerMarked(TrisCoordinates c) {
        return getCellState(c.getX(), c.getY()) == CellStateEnum.PlayerMarked;
    }

    private boolean isCellComputerMarked(TrisCoordinates c) {
        return getCellState(c.getX(), c.getY()) == CellStateEnum.ComputerMarked;
    }
}
