package component;

import TicTacToe.Cell.Matrix;
import TicTacToe.ComputerPlayer.Minimax;
import TicTacToe.Coordinates.Coordinates;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinimaxTest {

    @Test
    public void lastTieMove_worthZero() {
        Minimax minimax = new Minimax();
        Matrix matrix = new Matrix();

          matrix.playerMark(new Coordinates(0, 0));
        matrix.computerMark(new Coordinates(1, 0));
        matrix.computerMark(new Coordinates(2, 0));
        matrix.computerMark(new Coordinates(0, 1));
          matrix.playerMark(new Coordinates(2, 1));
          matrix.playerMark(new Coordinates(0, 2));
          matrix.playerMark(new Coordinates(1, 2));
        matrix.computerMark(new Coordinates(2, 2));

        int actual = minimax.calcolateMoveValue(new Coordinates(1, 1), matrix);
        assertEquals(0, actual);
    }

}
