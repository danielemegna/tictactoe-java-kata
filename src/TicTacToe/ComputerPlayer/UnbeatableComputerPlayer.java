package TicTacToe.ComputerPlayer;

import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;

public class UnbeatableComputerPlayer implements ComputerPlayer {

    private final Minimax minimax;

    public UnbeatableComputerPlayer() {
        this.minimax = new Minimax();
    }

    public Coordinates establishTheNextMove(Matrix matrix) {
        int maxMoveValue = -1;
        Coordinates maxValueCoordinates = null;

        for(Coordinates c : matrix.getEmptyCoordinates()) {
            int currentMoveValue = minimax.calcolateComputerMoveValue(c, matrix);
            if(currentMoveValue == 1)
                return c;

            if(currentMoveValue > maxMoveValue) {
                maxMoveValue = currentMoveValue;
                maxValueCoordinates = c;
            }
        }

        if(maxValueCoordinates == null)
            throw new ComputerPlayerChoiceException("UnbeatableComputerPlayer cannot establish the next move! :o");

        return maxValueCoordinates;
    }
}
