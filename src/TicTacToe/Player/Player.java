package TicTacToe.Player;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.Coordinates;

public abstract class Player {
    private final CellMarkSign playerCellSign;

    public Player(CellMarkSign playerCellSign) {
        this.playerCellSign = playerCellSign;
    }

    public CellMarkSign getPlayerCellSign() {
        return playerCellSign;
    }

    protected void markBoard(Coordinates move, Board board) {
        board.mark(move, getPlayerCellSign());
    }

    public abstract void doNextMove(Board board);
    public abstract String getName();
}
