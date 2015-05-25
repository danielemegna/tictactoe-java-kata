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
        for(int i = 0; i < 3; i++)
            Arrays.fill(this.cellsMatrix[i], CellStateEnum.Empty);
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

    public void playerMark(int x, int y) {
        this.cellsMatrix[x][y] = CellStateEnum.PlayerMarked;
    }
}
