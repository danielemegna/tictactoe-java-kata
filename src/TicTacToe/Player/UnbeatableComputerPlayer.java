package TicTacToe.Player;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.Coordinates;

public class UnbeatableComputerPlayer extends Player {

    private final Minimax minimax;

    public UnbeatableComputerPlayer(CellMarkSign cellPlayerSign) {
        super(cellPlayerSign);
        this.minimax = new Minimax(cellPlayerSign);
    }

    public void doNextMove(Board board) {
        Coordinates move = estabilishNextMove(board);
        markBoard(move, board);
    }

    public String getName() {
        return "UnbeatableComputerPlayer";
    }

    private Coordinates estabilishNextMove(Board board) {
        int maxMoveValue = Minimax.ADVERSARY_WIN_VALUE;
        Coordinates maxValueCoordinates = null;

        for(Coordinates move : board.getEmptyCoordinates()) {
            int currentMoveValue = minimax.calcolateMoveValue(move, board);
            if(currentMoveValue == Minimax.MY_ONEMOVEWIN_VALUE)
                return move;

            if(currentMoveValue > maxMoveValue) {
                maxMoveValue = currentMoveValue;
                maxValueCoordinates = move;
            }
        }

        if(maxValueCoordinates == null)
            throw new ComputerPlayerChoiceException("UnbeatableComputerPlayer cannot establish the next move!");

        return maxValueCoordinates;
    }
}
