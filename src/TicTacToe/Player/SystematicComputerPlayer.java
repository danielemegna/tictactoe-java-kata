package TicTacToe.Player;

import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Cell.Board;
import TicTacToe.Coordinates.Coordinates;

public class SystematicComputerPlayer extends Player {

    public SystematicComputerPlayer(CellMarkSign cellPlayerSign) {
        super(cellPlayerSign);
    }

    public void doNextMove(Board board) {

        for(int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                Coordinates c = new Coordinates(x, y);
                if (board.isCellEmpty(c)) {
                    markBoard(c, board);
                    return;
                }
            }
        }

        throw new ComputerPlayerChoiceException("SystematicComputerPlayer cannot find an Empty cell for its game");
    }

    public String getName() {
        return "SystematicComputerPlayer";
    }
}
