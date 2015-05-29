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

    public boolean isFull() {
        return false;
    }

    public boolean thereIsAWinner() {
        if(playerWon())
            return true;

        return false;
    }

    public void reset() {
        initEmptyCellsMatrix();
    }

    private boolean playerWon() {
        return
            (
                getCellState(0,0) == CellStateEnum.PlayerMarked &&
                getCellState(1,0) == CellStateEnum.PlayerMarked &&
                getCellState(2,0) == CellStateEnum.PlayerMarked
            ) ||
            (
                getCellState(0,0) == CellStateEnum.PlayerMarked &&
                getCellState(0,1) == CellStateEnum.PlayerMarked &&
                getCellState(0,2) == CellStateEnum.PlayerMarked
            ) ||
            (
                getCellState(0,1) == CellStateEnum.ComputerMarked &&
                getCellState(1,1) == CellStateEnum.ComputerMarked &&
                getCellState(2,1) == CellStateEnum.ComputerMarked
            );
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

    public String getPlayerName() {
        return playerName;
    }

    public void computerMark(int x, int y) {
        Cell c = getCell(x, y);
        c.computerMark();
    }

    public boolean isCellEmpty(int x, int y) {
        return getCell(x, y).isEmpty();
    }
}
