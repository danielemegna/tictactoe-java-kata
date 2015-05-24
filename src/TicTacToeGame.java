/**
 * Created by daniele on 21/05/15.
 */
public class TicTacToeGame {
    private String playerName;

    public TicTacToeGame(String playerName) {
        this.playerName = playerName;
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

        return CellStateEnum.Empty;
    }
}
