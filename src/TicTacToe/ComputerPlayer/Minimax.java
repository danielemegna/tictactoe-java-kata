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
        return calcolateComputerMoveValue(move, matrix, true);
    }

    private int calcolateComputerMoveValue(Coordinates move, Matrix matrix, boolean isComputerTurn) {
        Matrix clone = cloneAndMarkMatrix(move, matrix, isComputerTurn);

        Verdict v = referee.generateRefereeVerdict(clone);
        if(v.playerIsTheWinner())
            return -1;
        if(v.computerIsTheWinner())
            return 1;
        if(matrix.isFull())
            return 0;

        return valueOfRemainingEmptyPossibilities(clone, isComputerTurn);
    }

    private int valueOfRemainingEmptyPossibilities(Matrix matrix, boolean isComputerTurn) {
        int minValue = 0;
        int maxValue = 0;
        for(Coordinates c : matrix.getEmptyCoordinates()) {
            int value = calcolateComputerMoveValue(c, matrix, !isComputerTurn);
            minValue = Math.min(minValue, value);
            maxValue = Math.max(maxValue, value);
        }

        if(isComputerTurn)
            return minValue;

        return maxValue;
    }

    private Matrix cloneAndMarkMatrix(Coordinates c, Matrix matrix, boolean isComputerTurn) {
        Matrix clone = matrix.clone();
        if(isComputerTurn)
            clone.computerMark(c);
        else
            clone.playerMark(c);

        return clone;
    }
}
