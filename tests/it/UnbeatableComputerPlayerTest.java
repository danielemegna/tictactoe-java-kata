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
    public void choosesOneOneAsNotFirstMoveIfAvailable() {
        playerMark(0, 0);
        assertNextMove(1, 1);

        setup();

        playerMark(0, 1);
        assertNextMove(1, 1);

        setup();

        playerMark(1, 2);
        assertNextMove(1, 1);

        setup();

        playerMark(2, 2);
        assertNextMove(1, 1);
    }

    @Test
    public void choosesZeroZeroAsNotFirstMoveIfOneOneNotAvailable() {
        playerMark(1, 1);
        assertNextMove(0, 0);
    }

    @Test
    public void winSituation() {
        playerMark(0, 0);
        computerMark(1, 1);
        playerMark(2, 2);
        computerMark(0, 1);
        playerMark(1, 2);

        assertNextMove(2, 1);
    }

    private void assertNextMove(int x, int y) {
        Coordinates expected = new Coordinates(x, y);
        Coordinates actual = cp.establishTheNextMove(matrix);
        assertEquals(expected, actual);
    }

    private void playerMark(int x, int y) {
        matrix.playerMark(new Coordinates(x, y));
    }

    private void computerMark(int x, int y) {
        matrix.computerMark(new Coordinates(x, y));
    }
}
