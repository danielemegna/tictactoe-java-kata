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

    public int calcolateMoveValue(Coordinates move, Matrix matrix) {
        Matrix clone = matrix.clone();
        clone.computerMark(move);
        Verdict v = referee.generateRefereeVerdict(clone);
        if(v.computerIsTheWinner())
            return 1;

        return 0;
    }
}
