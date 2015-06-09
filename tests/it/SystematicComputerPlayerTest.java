package it;

import TicTacToe.Cell.Matrix;
import TicTacToe.ComputerPlayer.SystematicComputerPlayer;
import TicTacToe.Coordinates.Coordinates;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SystematicComputerPlayerTest {

    @Test
    public void withEmptyMatrix_SystematicComputerPlayerWillChooseZeroZero() {
        Matrix m = new Matrix();
        SystematicComputerPlayer cp = new SystematicComputerPlayer();

        assertEquals(new Coordinates(0, 0), cp.establishTheNextMove(m));
    }
}
