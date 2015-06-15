package TicTacToe.ComputerPlayer;

import TicTacToe.Cell.CellStateEnum;
import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;

public class UnbeatableComputerPlayer implements ComputerPlayer {

    public Coordinates establishTheNextMove(Matrix matrix) {
        if(matrix.getCellState(new Coordinates(1, 1)) == CellStateEnum.PlayerMarked)
            return new Coordinates(0, 0);

        return new Coordinates(1, 1);
    }
}
