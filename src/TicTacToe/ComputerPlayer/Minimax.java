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
        Matrix clone = cloneAndMarkMatrix(move, matrix, true);

        Verdict v = referee.generateRefereeVerdict(clone);
        if(v.computerIsTheWinner())
            return 1;
        if(clone.isFull())
            return 0;

        return valueOfRemainingEmptyPossibilities(clone);
    }

    private int valueOfRemainingEmptyPossibilities(Matrix matrix) {

        int minValue = 1;
        for(Coordinates c : matrix.getEmptyCoordinates()) {
            Matrix clone = cloneAndMarkMatrix(c, matrix, false);

            Verdict v = referee.generateRefereeVerdict(clone);
            if(v.playerIsTheWinner())
                return -1;
            if(clone.isFull()) {
                minValue = Math.min(0, minValue);
                continue;

            }

            int maxValue = -1;
            for(Coordinates c2 : clone.getEmptyCoordinates()) {
                int value = calcolateComputerMoveValue(c2, clone);
                maxValue = Math.max(maxValue, value);
            }
            minValue = Math.min(maxValue, minValue);
        }

        return minValue;
    }

    private Matrix cloneAndMarkMatrix(Coordinates c, Matrix matrix, boolean asComputer) {
        Matrix clone = matrix.clone();
        if(asComputer)
            clone.computerMark(c);
        else
            clone.playerMark(c);

        return clone;
    }
}
