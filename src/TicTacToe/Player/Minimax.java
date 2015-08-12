package TicTacToe.Player;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Referee.Referee;

public class Minimax {

    static final int MY_ONEMOVEWIN_VALUE = 2;
    static final int MY_WIN_VALUE = 1;
    static final int TIE_VALUE = 0;
    static final int ADVERSARY_WIN_VALUE = -1;

    private final Referee referee;
    private final CellMarkSign myCellSign;
    private final CellMarkSign adversaryCellSign;

    public Minimax(CellMarkSign myCellSign) {
        this.referee = new Referee();
        this.myCellSign = myCellSign;
        this.adversaryCellSign = CellMarkSign.values()[(myCellSign.ordinal()+1)%2];
    }

    public int calcolateMoveValue(Coordinates move, Board board) {
        Board clone = board.clone();
        clone.mark(move, myCellSign);

        CellMarkSign winnerCellMark = referee.getWinnerCellMark(clone);
        if(winnerCellMark == myCellSign)
            return MY_ONEMOVEWIN_VALUE;
        if(clone.isFull())
            return TIE_VALUE;

        return minPlayerMoveValueChoosable(clone);
    }

    private int minPlayerMoveValueChoosable(Board board) {
        int minPlayerMoveValue = MY_WIN_VALUE;

        for(Coordinates c : board.getEmptyCoordinates()) {
            Board clone = board.clone();
            clone.mark(c, adversaryCellSign);

            CellMarkSign winnerCellMark = referee.getWinnerCellMark(clone);
            if(winnerCellMark == adversaryCellSign)
                return ADVERSARY_WIN_VALUE;

            if(clone.isFull()) {
                minPlayerMoveValue = Math.min(TIE_VALUE, minPlayerMoveValue);
                continue;
            }

            int maxComputerMoveValue = maxComputerMoveValueChoosable(clone);
            minPlayerMoveValue = Math.min(minPlayerMoveValue, maxComputerMoveValue);
        }

        return minPlayerMoveValue;
    }

    private int maxComputerMoveValueChoosable(Board clone) {
        int maxMoveValue = ADVERSARY_WIN_VALUE;

        for(Coordinates c : clone.getEmptyCoordinates()) {
            int currentMoveValue = calcolateMoveValue(c, clone);
            if(currentMoveValue == MY_ONEMOVEWIN_VALUE)
                return MY_WIN_VALUE;

            maxMoveValue = Math.max(maxMoveValue, currentMoveValue);
        }

        return maxMoveValue;
    }

}