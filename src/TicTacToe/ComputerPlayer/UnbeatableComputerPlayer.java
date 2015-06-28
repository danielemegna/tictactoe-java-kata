package TicTacToe.ComputerPlayer;

import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;

public class UnbeatableComputerPlayer implements ComputerPlayer {

    private final Minimax minimax;

    public UnbeatableComputerPlayer() {
        this.minimax = new Minimax();
    }

    public Coordinates establishTheNextMove(Matrix matrix) {
        int maxValue = -1000;
        Coordinates maxValueCoordinates = null;
        for(Coordinates c : matrix.getEmptyCoordinates()) {
            int value = minimax.calcolateComputerMoveValue(c, matrix);
            if(value > maxValue) {
                maxValue = value;
                maxValueCoordinates = c;
            }
        }

        if(maxValueCoordinates == null)
            throw new ComputerPlayerChoiceException("UnbeatableComputerPlayer cannot establish the next move");

        return maxValueCoordinates;
    }
}
