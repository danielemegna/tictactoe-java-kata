package TicTacToe.ComputerPlayer;

import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;

public class UnbeatableComputerPlayer implements ComputerPlayer {

    public Coordinates establishTheNextMove(Matrix matrix) {
        return new Coordinates(0, 0);
    }
}
