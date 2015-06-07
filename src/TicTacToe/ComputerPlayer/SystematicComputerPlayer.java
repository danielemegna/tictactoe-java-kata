package TicTacToe.ComputerPlayer;

import TicTacToe.Cell.CellStateEnum;
import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;

public class SystematicComputerPlayer implements ComputerPlayer {

    public Coordinates establishTheNextMove(Matrix matrix) {
        for(int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                Coordinates c = new Coordinates(x, y);
                if (matrix.getCellState(c) == CellStateEnum.Empty)
                    return c;
            }
        }

        throw new RuntimeException("SystematicComputerPlayer cannot find an Empty cell for its game");
    }
}
