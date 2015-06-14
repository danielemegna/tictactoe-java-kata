package it;

import TicTacToe.Cell.Matrix;
import TicTacToe.ComputerPlayer.ComputerPlayer;
import TicTacToe.ComputerPlayer.UnbeatableComputerPlayer;
import TicTacToe.Coordinates.Coordinates;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnbeatableComputerPlayerTest {

    private ComputerPlayer cp;
    private Matrix matrix;

    @Before
    public void setup() {
        cp = new UnbeatableComputerPlayer();
        matrix = new Matrix();
    }

    @Test
    public void cpAlwaysChoosesZeroZeroAsFirstMove() {
        assertNextMove(0, 0);
    }

    @Test
    public void choosesOneOneAsNotFirstMoveIfAvailable() {
        matrix.playerMark(new Coordinates(0, 0));
        assertNextMove(1, 1);
    }

    private void assertNextMove(int x, int y) {
        Coordinates expected = new Coordinates(x, y);
        Coordinates actual = cp.establishTheNextMove(matrix);
        assertEquals(expected, actual);
    }
}
