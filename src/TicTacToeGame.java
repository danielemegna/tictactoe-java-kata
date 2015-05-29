import TicTacToeCell.CellStateEnum;
import Coordinates.TicTacToeCoordinates;

public class TicTacToeGame {

    private String playerName;
    private CellMatrix matrix;

    public TicTacToeGame(String playerName) {
        this.playerName = playerName;
        this.matrix = new CellMatrix();
    }

    public CellStateEnum getCellState(int x, int y) {
        return matrix.getCellState(new TicTacToeCoordinates(x, y));
    }

    public void playerMark(int x, int y) {
        matrix.playerMark(new TicTacToeCoordinates(x, y));
    }

    public void computerMark(int x, int y) {
        matrix.computerMark(new TicTacToeCoordinates(x, y));
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
            isCellPlayerMarked(new TicTacToeCoordinates(0,0)) &&
            isCellPlayerMarked(new TicTacToeCoordinates(1,0)) &&
            isCellPlayerMarked(new TicTacToeCoordinates(2,0))
        ) ||
        (
            isCellPlayerMarked(new TicTacToeCoordinates(0,0)) &&
            isCellPlayerMarked(new TicTacToeCoordinates(0,1)) &&
            isCellPlayerMarked(new TicTacToeCoordinates(0,2))
        ) ||
        (
            isCellPlayerMarked(new TicTacToeCoordinates(0,2)) &&
            isCellPlayerMarked(new TicTacToeCoordinates(1,2)) &&
            isCellPlayerMarked(new TicTacToeCoordinates(2,2))
        );
    }

    public boolean computerWon() {
        return
        (
            isCellComputerMarked(new TicTacToeCoordinates(0,1)) &&
            isCellComputerMarked(new TicTacToeCoordinates(1,1)) &&
            isCellComputerMarked(new TicTacToeCoordinates(2,1))
        );
    }

    public void reset() {
        this.matrix = new CellMatrix();
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isCellEmpty(int x, int y) {
        return matrix.isCellEmpty(new TicTacToeCoordinates(x, y));
    }

    private boolean isCellPlayerMarked(TicTacToeCoordinates c) {
        return getCellState(c.getX(), c.getY()) == CellStateEnum.PlayerMarked;
    }

    private boolean isCellComputerMarked(TicTacToeCoordinates c) {
        return getCellState(c.getX(), c.getY()) == CellStateEnum.ComputerMarked;
    }
}
