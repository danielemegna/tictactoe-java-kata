package it;

import TicTacToe.Cell.Matrix;
import TicTacToe.ComputerPlayer.ComputerPlayer;
import TicTacToe.ComputerPlayer.UnbeatableComputerPlayer;
import TicTacToe.Coordinates.Coordinates;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnbeatableComputerPlayerTest {

    @Test
    public void cpAlwaysChoosesZeroZeroAsFirstMove() {
        ComputerPlayer cp = new UnbeatableComputerPlayer();
        Matrix matrix = new Matrix();

        Coordinates c = cp.establishTheNextMove(matrix);
        assertEquals(new Coordinates(0, 0), c);
    }
}
