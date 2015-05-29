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
        return getCell(x, y).getState();
    }

    public void playerMark(int x, int y) {
        getCell(x, y).playerMark();
    }

    public void computerMark(int x, int y) {
        getCell(x, y).computerMark();
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
            isCellPlayerMarked(0,0) &&
            isCellPlayerMarked(1,0) &&
            isCellPlayerMarked(2,0)
        ) ||
        (
            isCellPlayerMarked(0,0) &&
            isCellPlayerMarked(0,1) &&
            isCellPlayerMarked(0,2)
        );
    }

    public boolean computerWon() {
        return
        (
            isCellComputerMarked(0, 1) &&
            isCellComputerMarked(1, 1) &&
            isCellComputerMarked(2,1)
        );
    }

    public void reset() {
        initEmptyCellsMatrix();
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isCellEmpty(int x, int y) {
        return getCell(x, y).isEmpty();
    }

    private void initEmptyCellsMatrix() {
        this.cellsMatrix = new Cell[3][3];
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                this.cellsMatrix[x][y] = new Cell();

    }

    private Cell getCell(int x, int y) {
        try {
            return cellsMatrix[x][y];
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new CoordinateOutOfBoundsException();
        }
    }

    private boolean isCellPlayerMarked(int x, int y) {
        return getCellState(x,y) == CellStateEnum.PlayerMarked;
    }

    private boolean isCellComputerMarked(int x, int y) {
        return getCellState(x,y) == CellStateEnum.ComputerMarked;
    }
}
