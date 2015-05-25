import java.util.Arrays;

/**
 * Created by daniele on 21/05/15.
 */
public class TicTacToeGame {

    private String playerName;
    private CellStateEnum[][] cellsMatrix;

    public TicTacToeGame(String playerName) {
        this.playerName = playerName;
        initEmptyCellsMatrix();
    }

    private void initEmptyCellsMatrix() {
        this.cellsMatrix = new CellStateEnum[3][3];
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                setCellState(x, y, CellStateEnum.Empty);

    }

    public boolean isFull() {
        return false;
    }

    public boolean thereIsAWinner() {
        return false;
    }

    public CellStateEnum getCellState(int x, int y) {
        try {
            return this.cellsMatrix[x][y];
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new CoordinateOutOfBoundsException();
        }
    }

    private void setCellState(int x, int y, CellStateEnum newState) {
        try {
            this.cellsMatrix[x][y] = newState;
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new CoordinateOutOfBoundsException();
        }
    }

    public void playerMark(int x, int y) {
        if(getCellState(x, y) != CellStateEnum.Empty)
            throw new AlreadyMarkedCellAttemptException();

        setCellState(x, y, CellStateEnum.PlayerMarked);
    }
}
