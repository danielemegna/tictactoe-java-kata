package TicTacToe.ComputerPlayer;

import TicTacToe.Cell.CellStateEnum;
import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;

public class UnbeatableComputerPlayer implements ComputerPlayer {

    public Coordinates establishTheNextMove(Matrix matrix) {
        Coordinates c;

        c = winSituation(matrix);
        if(c != null)
            return c;

        if(matrix.getCellState(new Coordinates(1, 1)) == CellStateEnum.PlayerMarked)
            return new Coordinates(0, 0);

        return new Coordinates(1, 1);
    }

    private Coordinates winSituation(Matrix matrix) {
        if (matrix.getCellState(new Coordinates(0, 1)) == CellStateEnum.ComputerMarked &&
            matrix.getCellState(new Coordinates(1, 1)) == CellStateEnum.ComputerMarked &&
            matrix.getCellState(new Coordinates(2, 1)) == CellStateEnum.Empty) {
            return new Coordinates(2, 1);
        }

        return null;
    }

}
