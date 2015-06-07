package TicTacToe;

import TicTacToe.Cell.CellStateEnum;
import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;

public class SystematicComputerPlayer implements ComputerPlayer {

    public void doAGame(Game game, Matrix matrix) {
        Coordinates c = findFreeCoordinates(matrix);
        game.computerMark(c.getX(), c.getY());
    }

    private Coordinates findFreeCoordinates(Matrix m) {
        for(int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                Coordinates c = new Coordinates(x, y);
                if (m.getCellState(c) == CellStateEnum.Empty)
                    return c;
            }
        }

        throw new RuntimeException("SystematicComputerPlayer cannot find an Empty cell for its game");
    }
}
