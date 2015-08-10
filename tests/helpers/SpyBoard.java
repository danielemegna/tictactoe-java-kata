package helpers;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.Coordinates;

public class SpyBoard extends Board {

    private Coordinates lastMarkCoordinates = null;
    private CellMarkSign lastMarkSign = null;

    @Override
    public void mark(Coordinates c, CellMarkSign sign) {
        super.mark(c, sign);
        lastMarkCoordinates = c;
        lastMarkSign = sign;
    }

    public Coordinates getLastMarkCoordinates() {
        return lastMarkCoordinates;
    }

    public CellMarkSign getLastMarkSign() {
        return lastMarkSign;
    }
}
