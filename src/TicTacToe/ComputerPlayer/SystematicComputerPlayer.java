package TicTacToe.ComputerPlayer;

import TicTacToe.Cell.CellState;
import TicTacToe.Cell.Board;
import TicTacToe.Coordinates.Coordinates;

public class SystematicComputerPlayer implements ComputerPlayer {

    public Coordinates establishTheNextMove(Board board) {

        for(int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                Coordinates c = new Coordinates(x, y);
                if (board.getCellState(c) == CellState.Empty)
                    return c;
            }
        }

        throw new ComputerPlayerChoiceException("SystematicComputerPlayer cannot find an Empty cell for its game");
    }
}
