package TicTacToe.Player;

import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Cell.Board;
import TicTacToe.Coordinates.Coordinates;

import java.util.Random;

public class RandomComputerPlayer extends Player {

    private final Random random;

    public RandomComputerPlayer(CellMarkSign cellStateAfterMark) {
        super(cellStateAfterMark);
        random = new Random();
    }


    public void doNextMove(Board board) {
        while(true) {
            Coordinates c = new Coordinates(random.nextInt(3), random.nextInt(3));
            if(board.isCellEmpty(c)) {
                markBoard(c, board);
                return;
            }
        }
    }

    @Override
    public String getName() {
        return "RandomComputerPlayer";
    }
}
