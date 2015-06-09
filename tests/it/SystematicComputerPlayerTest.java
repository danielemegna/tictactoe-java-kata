package it;

import TicTacToe.Cell.Matrix;
import TicTacToe.ComputerPlayer.ComputerPlayerChoiceException;
import TicTacToe.ComputerPlayer.SystematicComputerPlayer;
import TicTacToe.Coordinates.Coordinates;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SystematicComputerPlayerTest {

    private Matrix m;
    private SystematicComputerPlayer cp;

    @Before
    public void setup() {
        this.m = new Matrix();
        this.cp = new SystematicComputerPlayer();
    }

    @Test
    public void withEmptyMatrix_SystematicComputerPlayerWillChooseZeroZero() {
        assertNextMoveAndMarkIt(new Coordinates(0, 0));
    }

    @Test
    public void SystematicComputer_isAlwaysPredictable() {
        m.playerMark(new Coordinates(0, 0));
        assertNextMoveAndMarkIt(new Coordinates(1, 0));
        m.playerMark(new Coordinates(2, 0));
        assertNextMoveAndMarkIt(new Coordinates(0, 1));
        m.playerMark(new Coordinates(2, 1));
        assertNextMoveAndMarkIt(new Coordinates(1, 1));
        m.playerMark(new Coordinates(1, 2));
        assertNextMoveAndMarkIt(new Coordinates(0, 2));
        assertNextMoveAndMarkIt(new Coordinates(2, 2));

        try {
            cp.establishTheNextMove(m);
            fail("Expected ComputerPlayerChoiceException not catched");
        } catch(ComputerPlayerChoiceException ex) {
            assertEquals("SystematicComputerPlayer cannot find an Empty cell for its game", ex.getMessage());
        }
    }

    private void assertNextMoveAndMarkIt(Coordinates expected) {
        assertEquals(expected, cp.establishTheNextMove(m));
        m.computerMark(expected);
    }
}
