package component;

import TicTacToe.Cell.Matrix;
import TicTacToe.ComputerPlayer.Minimax;
import TicTacToe.Coordinates.Coordinates;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinimaxTest {

    private Minimax minimax;
    private Matrix matrix;

    @Before
    public void setup() {
        this.minimax = new Minimax();
        this.matrix = new Matrix();
    }

    @Test
    public void lastTieMove_worthZero() {
          playerMark(0, 0);
        computerMark(1, 0);
        computerMark(2, 0);
        computerMark(0, 1);
          playerMark(2, 1);
          playerMark(0, 2);
          playerMark(1, 2);
        computerMark(2, 2);

        int actual = minimax.calcolateMoveValue(new Coordinates(1, 1), matrix);
        assertEquals(0, actual);
    }

    @Test
    public void computerWinnerWithAMoveAndTwoPossibilities() {
          playerMark(0, 0);
        computerMark(1, 0);
          playerMark(2, 0);
          playerMark(0, 1);
        computerMark(1, 1);
          playerMark(2, 1);
        computerMark(0, 2);

        int computerWinnerMoveValue = minimax.calcolateMoveValue(new Coordinates(1, 2), matrix);
        assertEquals(1, computerWinnerMoveValue);

        int tieMoveValue = minimax.calcolateMoveValue(new Coordinates(2, 2), matrix);
        assertEquals(0, tieMoveValue);
    }

    private void playerMark(int x, int y) {
        matrix.playerMark(new Coordinates(x, y));
    }

    private void computerMark(int x, int y) {
        matrix.computerMark(new Coordinates(x, y));
    }

}
