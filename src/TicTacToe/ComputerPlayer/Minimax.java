package TicTacToe.ComputerPlayer;

import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Referee.Referee;
import TicTacToe.Referee.Verdict;

public class Minimax {

    private final Referee referee;

    public Minimax() {
        this.referee = new Referee();
    }

    public int calcolateComputerMoveValue(Coordinates move, Matrix matrix) {
        Matrix clone = matrix.clone();
        clone.computerMark(move);

        Verdict v = referee.generateRefereeVerdict(clone);
        if(v.computerIsTheWinner())
            return 1;
        if(clone.isFull())
            return 0;

        return minPlayerMoveValueChoosable(clone);
    }

    private int minPlayerMoveValueChoosable(Matrix matrix) {
        int minPlayerMoveValue = 1;

        for(Coordinates c : matrix.getEmptyCoordinates()) {
            Matrix clone = matrix.clone();
            clone.playerMark(c);

            Verdict v = referee.generateRefereeVerdict(clone);
            if(v.playerIsTheWinner())
                return -1;

            if(clone.isFull()) {
                minPlayerMoveValue = Math.min(0, minPlayerMoveValue);
                continue;
            }

            int maxComputerMoveValue = maxComputerMoveValueChoosable(clone);
            minPlayerMoveValue = Math.min(minPlayerMoveValue, maxComputerMoveValue);
        }

        return minPlayerMoveValue;
    }

    private int maxComputerMoveValueChoosable(Matrix clone) {
        int maxMoveValue = -1;

        for(Coordinates c : clone.getEmptyCoordinates()) {
            int currentMoveValue = calcolateComputerMoveValue(c, clone);
            if(currentMoveValue == 1)
                return 1;

            maxMoveValue = Math.max(maxMoveValue, currentMoveValue);
        }

        return maxMoveValue;
    }

}
