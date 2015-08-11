package helpers;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Player.Player;

public class SpyPlayer extends Player {
    private Coordinates nextMove = null;
    private String lastCalledMethod = null;

    public SpyPlayer(CellMarkSign sign) {
        super(sign);
    }

    @Override
    public void doNextMove(Board board) {
        lastCalledMethod = "doNextMove()";
    }

    @Override
    public String getName() {
        return "SpyPlayer";
    }

    public void setNextMove(Coordinates nextMove) {
        this.nextMove = nextMove;
    }

    public String getLastCalledMethod() {
        return lastCalledMethod;
    }
}
