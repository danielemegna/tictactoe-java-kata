package it;

import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Display.Display;
import TicTacToe.Display.TerminalDisplay;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TerminalDisplayTest {

    @Test
    public void displayAnEmptyMatrix() {
        Display d = new TerminalDisplay();
        String expected = "- - -\n- - -\n- - -";
        assertEquals(expected, d.show(new Matrix()));
    }
}
