package TicTacToe.ComputerPlayer;

import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Referee.Referee;
import TicTacToe.Referee.Verdict;

import java.util.Set;

public class Minimax {

    private final Referee referee;

    public Minimax() {
        this.referee = new Referee();
    }

    public int calcolateMoveValue(Coordinates move, Matrix matrix) {
        Integer moveValue;
        Matrix mainClone = matrix.clone();
        mainClone.computerMark(move);

        moveValue = calcolateMatrixVerdictValue(mainClone);
        if(moveValue != null)
            return moveValue;

        int sum = 0;
        Set<Coordinates> mainEmptyCoordinates = mainClone.getEmptyCoordinates();
        for(Coordinates mainEmpty : mainEmptyCoordinates) {
            Matrix internalClone = mainClone.clone();
            internalClone.playerMark(mainEmpty);

            moveValue = calcolateMatrixVerdictValue(internalClone);
            if(moveValue != null) {
                sum += moveValue;
                continue;
            }

            Set<Coordinates> internalEmptyCoordinates = internalClone.getEmptyCoordinates();
            for(Coordinates internalEmpty : internalEmptyCoordinates) {
                sum += calcolateMoveValue(internalEmpty, internalClone);
            }
        }

        return sum;
    }

    private Integer calcolateMatrixVerdictValue(Matrix m) {
        Verdict v = referee.generateRefereeVerdict(m);
        if(v.playerIsTheWinner())
            return -1;
        if(v.computerIsTheWinner())
            return 1;
        if(m.isFull())
            return 0;

        return null;
    }
}
