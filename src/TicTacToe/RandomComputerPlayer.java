package TicTacToe;

import TicTacToe.Cell.CellStateEnum;
import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;

import java.util.Random;

public class RandomComputerPlayer implements ComputerPlayer {

    private final Random random;

    public RandomComputerPlayer() {
        random = new Random();
    }


    public Coordinates establishTheNextMove(Matrix matrix) {
        while(true) {
            Coordinates c = new Coordinates(random.nextInt(3), random.nextInt(3));
            if(matrix.getCellState(c) == CellStateEnum.Empty)
                return c;
        }
    }
}
