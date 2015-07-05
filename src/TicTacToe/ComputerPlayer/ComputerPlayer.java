package TicTacToe.ComputerPlayer;

import TicTacToe.Cell.Board;
import TicTacToe.Coordinates.Coordinates;

public interface ComputerPlayer {
    Coordinates establishTheNextMove(Board board);
}
