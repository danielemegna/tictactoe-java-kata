/**
 * Created by daniele on 21/05/15.
 */
public class TicTacToeGame {

    private String playerName;
    private Cell[][] cellsMatrix;

    public TicTacToeGame(String playerName) {
        this.playerName = playerName;
        initEmptyCellsMatrix();
    }

    public CellStateEnum getCellState(int x, int y) {
        Coordinates c = new Coordinates(x, y);
        return getCell(c).getState();
    }

    public void playerMark(int x, int y) {
        Coordinates c = new Coordinates(x, y);
        getCell(c).playerMark();
    }

    public void computerMark(int x, int y) {
        Coordinates c = new Coordinates(x, y);
        getCell(c).computerMark();
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
        initEmptyCellsMatrix();
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isCellEmpty(int x, int y) {
        Coordinates c = new Coordinates(x, y);
        return getCell(c).isEmpty();
    }

    private void initEmptyCellsMatrix() {
        this.cellsMatrix = new Cell[3][3];
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                this.cellsMatrix[x][y] = new Cell();

    }

    private Cell getCell(Coordinates c) {
        try {
            return cellsMatrix[c.getX()][c.getY()];
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new CoordinateOutOfBoundsException();
        }
    }

    private boolean isCellPlayerMarked(Coordinates c) {
        return getCellState(c.getX(), c.getY()) == CellStateEnum.PlayerMarked;
    }

    private boolean isCellComputerMarked(Coordinates c) {
        return getCellState(c.getX(), c.getY()) == CellStateEnum.ComputerMarked;
    }
}
