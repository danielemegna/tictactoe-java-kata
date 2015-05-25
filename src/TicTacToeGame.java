/**
 * Created by daniele on 21/05/15.
 */
public class TicTacToeGame {

    private String playerName;
    private CellStateEnum[][] cellState;

    public TicTacToeGame(String playerName) {
        this.playerName = playerName;
        this.cellState = new CellStateEnum[3][3];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                this.cellState[i][j] = CellStateEnum.Empty;
    }

    public boolean isFull() {
        return false;
    }

    public boolean thereIsAWinner() {
        return false;
    }

    public CellStateEnum getCellState(int x, int y) {
        if(x > 2 || y > 2)
            throw new CoordinateOutOfBoundsException();

        return this.cellState[x][y];
    }

    public void playerMark(int x, int y) {
        this.cellState[x][y] = CellStateEnum.PlayerMarked;
    }
}
