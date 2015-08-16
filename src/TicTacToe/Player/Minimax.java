package TicTacToe.Player;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Game.Referee;

public class Minimax {

    static final int MY_ONEMOVEWIN_VALUE = 2;
    static final int MY_WIN_VALUE = 1;
    static final int TIE_VALUE = 0;
    static final int ADVERSARY_WIN_VALUE = -1;

    private final CellMarkSign myCellSign;
    private final CellMarkSign adversaryCellSign;
    private final Referee referee;

    public Minimax(CellMarkSign myCellSign) {
        this.myCellSign = myCellSign;
        this.adversaryCellSign = calcolateAdversaryCellSign();
        this.referee = new Referee();
    }

    public int calcolateMoveValue(Coordinates move, Board board) {
        Board clone = board.clone();
        clone.mark(move, myCellSign);

        CellMarkSign winnerCellMark = referee.getWinnerCellMark(clone);
        if(winnerCellMark == myCellSign)
            return MY_ONEMOVEWIN_VALUE;
        if(clone.isFull())
            return TIE_VALUE;

        return minAdversaryChoosableMoveValue(clone);
    }

    private int minAdversaryChoosableMoveValue(Board board) {
        int minAdversaryMoveValue = MY_WIN_VALUE;

        for(Coordinates c : board.getEmptyCoordinates()) {
            Board clone = board.clone();
            clone.mark(c, adversaryCellSign);

            CellMarkSign winnerCellMark = referee.getWinnerCellMark(clone);
            if(winnerCellMark == adversaryCellSign)
                return ADVERSARY_WIN_VALUE;

            if(clone.isFull()) {
                minAdversaryMoveValue = Math.min(TIE_VALUE, minAdversaryMoveValue);
                continue;
            }

            int maxMyMoveValue = maxChoosableMoveValue(clone);
            minAdversaryMoveValue = Math.min(minAdversaryMoveValue, maxMyMoveValue);
        }

        return minAdversaryMoveValue;
    }

    private int maxChoosableMoveValue(Board board) {
        int maxMoveValue = ADVERSARY_WIN_VALUE;

        for(Coordinates c : board.getEmptyCoordinates()) {
            int currentMoveValue = calcolateMoveValue(c, board);
            if(currentMoveValue == MY_ONEMOVEWIN_VALUE)
                return MY_WIN_VALUE;

            maxMoveValue = Math.max(maxMoveValue, currentMoveValue);
        }

        return maxMoveValue;
    }

    private CellMarkSign calcolateAdversaryCellSign() {
        int numberOfSigns = CellMarkSign.values().length;
        int myCellSignIndex = myCellSign.ordinal();
        int nextCellSignIndex = (myCellSignIndex + 1) % numberOfSigns;
        return CellMarkSign.values()[nextCellSignIndex];
    }
}