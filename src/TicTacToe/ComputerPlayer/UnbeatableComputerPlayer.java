package TicTacToe.ComputerPlayer;

import TicTacToe.Cell.CellState;
import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;

public class UnbeatableComputerPlayer implements ComputerPlayer {

    public Coordinates establishTheNextMove(Matrix matrix) {
        Coordinates c;

        c = winSituation(matrix);
        if(c != null)
            return c;

        if(isPlayerMarked(1, 1, matrix))
            return new Coordinates(0, 0);

        return new Coordinates(1, 1);
    }

    private Coordinates winSituation(Matrix matrix) {
        if (isComputerMarked(0, 1, matrix) &&
            isComputerMarked(1, 1, matrix) &&
            isEmpty(2, 1, matrix)) {
            return new Coordinates(2, 1);
        }

        return null;
    }

    private boolean isEmpty(int x, int y, Matrix matrix) {
        return matrix.getCellState(new Coordinates(x, y)) == CellState.Empty;
    }

    private boolean isComputerMarked(int x, int y, Matrix matrix) {
        return matrix.getCellState(new Coordinates(x, y)) == CellState.ComputerMarked;
    }

    private boolean isPlayerMarked(int x, int y, Matrix matrix) {
        return matrix.getCellState(new Coordinates(x, y)) == CellState.PlayerMarked;
    }

}
