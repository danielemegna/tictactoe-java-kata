package TicTacToe.ComputerPlayer;

import TicTacToe.Cell.CellState;
import TicTacToe.Cell.Board;
import TicTacToe.Coordinates.Coordinates;

import java.util.Random;

public class RandomComputerPlayer implements ComputerPlayer {

    private final Random random;

    public RandomComputerPlayer() {
        random = new Random();
    }


    public Coordinates establishTheNextMove(Board board) {
        while(true) {
            Coordinates c = new Coordinates(random.nextInt(3), random.nextInt(3));
            if(board.getCellState(c) == CellState.Empty)
                return c;
        }
    }
}
