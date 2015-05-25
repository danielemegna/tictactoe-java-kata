/**
 * Created by daniele on 21/05/15.
 */
public class TicTacToeGame {

    private String playerName;
    private CellStateEnum cellState;

    public TicTacToeGame(String playerName) {
        this.playerName = playerName;
        this.cellState = CellStateEnum.Empty;
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

        return this.cellState;
    }

    public void playerMark(int x, int y) {
        this.cellState = CellStateEnum.PlayerMarked;
    }
}
