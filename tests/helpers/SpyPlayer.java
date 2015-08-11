package helpers;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Player.Player;

import java.util.ArrayList;
import java.util.List;

public class SpyPlayer extends Player {
    private List<String> calls = new ArrayList<>();

    public SpyPlayer(CellMarkSign sign) {
        super(sign);
    }

    @Override
    public void doNextMove(Board board) {
        // TODO
    }

    @Override
    public String getName() {
        return "SpyPlayer";
    }
}
