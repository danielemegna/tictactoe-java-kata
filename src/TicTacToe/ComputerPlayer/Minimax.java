package TicTacToe.ComputerPlayer;

import TicTacToe.Cell.Board;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Referee.Referee;
import TicTacToe.Referee.Verdict;

public class Minimax {

    static final int COMPUTER_ONEMOVEWIN_VALUE = 2;
    static final int COMPUTER_WIN_VALUE = 1;
    static final int TIE_VALUE = 0;
    static final int PLAYER_WIN_VALUE = -1;

    private final Referee referee;

    public Minimax() {
        this.referee = new Referee();
    }

    public int calcolateComputerMoveValue(Coordinates move, Board board) {
        Board clone = board.clone();
        clone.computerMark(move);

        Verdict v = referee.generateRefereeVerdict(clone);
        if(v == Verdict.computerIsTheWinner)
            return COMPUTER_ONEMOVEWIN_VALUE;
        if(clone.isFull())
            return TIE_VALUE;

        return minPlayerMoveValueChoosable(clone);
    }

    private int minPlayerMoveValueChoosable(Board board) {
        int minPlayerMoveValue = COMPUTER_WIN_VALUE;

        for(Coordinates c : board.getEmptyCoordinates()) {
            Board clone = board.clone();
            clone.playerMark(c);

            Verdict v = referee.generateRefereeVerdict(clone);
            if(v == Verdict.playerIsTheWinner)
                return PLAYER_WIN_VALUE;

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
        int maxMoveValue = PLAYER_WIN_VALUE;

        for(Coordinates c : clone.getEmptyCoordinates()) {
            int currentMoveValue = calcolateComputerMoveValue(c, clone);
            if(currentMoveValue == COMPUTER_ONEMOVEWIN_VALUE)
                return COMPUTER_WIN_VALUE;

            maxMoveValue = Math.max(maxMoveValue, currentMoveValue);
        }

        return maxMoveValue;
    }

}