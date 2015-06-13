package it;

import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Display.Display;
import TicTacToe.Display.TerminalDisplay;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TerminalDisplayTest {

    private Display display;
    private Matrix matrix;

    @Before
    public void setup() {
        display = new TerminalDisplay();
        matrix = new Matrix();
    }

    @Test
    public void showAnEmptyMatrix() {
        String expected = "- - -\n" +
                          "- - -\n" +
                          "- - -";

        assertEquals(expected, display.show(matrix));
    }

    @Test
    public void showAPlayerMarkAsX() {
        String expected = "- X -\n" +
                          "- - -\n" +
                          "- - -";

        matrix.playerMark(new Coordinates(1, 0));
        assertEquals(expected, display.show(matrix));
    }

    @Test
    public void showSomePlayerMarks() {
        String expected = "- X -\n" +
                          "X - -\n" +
                          "- X X";

        matrix.playerMark(new Coordinates(1, 0));
        matrix.playerMark(new Coordinates(0, 1));
        matrix.playerMark(new Coordinates(1, 2));
        matrix.playerMark(new Coordinates(2, 2));

        assertEquals(expected, display.show(matrix));
    }
}
