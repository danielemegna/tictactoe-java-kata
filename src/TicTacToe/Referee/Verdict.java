package TicTacToe.Referee;

import TicTacToe.Cell.CellMarkSign;

public class Verdict {
    private final CellMarkSign signWinner;

    public Verdict(CellMarkSign signWinner) {
        this.signWinner = signWinner;
    }

    public CellMarkSign getWinnerSign() {
        return signWinner;
    }

    public boolean thereIsAWinner() {
        return signWinner != null;
    }
}
