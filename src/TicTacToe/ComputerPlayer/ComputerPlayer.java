package TicTacToe.ComputerPlayer;

import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;

public interface ComputerPlayer {
    Coordinates establishTheNextMove(Matrix matrix);
}