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

    @Test
    public void SystematicComputer_isAlwaysPredictable() {
        Matrix m = new Matrix();
        SystematicComputerPlayer cp = new SystematicComputerPlayer();

        m.playerMark(new Coordinates(0, 0));
        assertEquals(new Coordinates(1, 0), cp.establishTheNextMove(m));
        m.computerMark(new Coordinates(1, 0));
        m.playerMark(new Coordinates(2, 0));
        assertEquals(new Coordinates(0, 1), cp.establishTheNextMove(m));
        m.computerMark(new Coordinates(0, 1));
        m.playerMark(new Coordinates(2, 1));
        assertEquals(new Coordinates(1, 1), cp.establishTheNextMove(m));
        m.computerMark(new Coordinates(1, 1));
    }
}
