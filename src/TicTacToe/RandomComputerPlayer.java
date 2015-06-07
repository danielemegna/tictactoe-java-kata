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

    public void doAGame(Game game, Matrix matrix) {
        Coordinates c = findFreeCoordinates(matrix);
        game.computerMark(c.getX(), c.getY());
    }

    private Coordinates findFreeCoordinates(Matrix m) {
        while(true) {
            Coordinates c = new Coordinates(random.nextInt(3), random.nextInt(3));
            if(m.getCellState(c) == CellStateEnum.Empty)
                return c;
        }
    }
}
