package TicTacToe.ComputerPlayer;

import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Referee.Referee;
import TicTacToe.Referee.Verdict;

public class Minimax {
    public int calcolateMoveValue(Coordinates move, Matrix matrix) {

        Matrix clone = matrix.clone();
        clone.computerMark(move);

        Referee r = new Referee();
        Verdict v = r.generateRefereeVerdict(clone);

        if(v.computerIsTheWinner())
            return 1;

        return 0;
    }
}
