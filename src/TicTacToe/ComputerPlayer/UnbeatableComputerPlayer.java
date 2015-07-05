package TicTacToe.ComputerPlayer;

import TicTacToe.Cell.Board;
import TicTacToe.Coordinates.Coordinates;

public class UnbeatableComputerPlayer implements ComputerPlayer {

    private final Minimax minimax;

    public UnbeatableComputerPlayer() {
        this.minimax = new Minimax();
    }

    public Coordinates establishTheNextMove(Board board) {
        int maxMoveValue = Minimax.PLAYER_WIN_VALUE;
        Coordinates maxValueCoordinates = null;

        for(Coordinates c : board.getEmptyCoordinates()) {
            int currentMoveValue = minimax.calcolateComputerMoveValue(c, board);
            if(currentMoveValue == Minimax.COMPUTER_ONEMOVEWIN_VALUE)
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
