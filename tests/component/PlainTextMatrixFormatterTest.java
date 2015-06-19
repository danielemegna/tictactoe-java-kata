package component;

import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Display.MatrixFormatter;
import TicTacToe.Display.PlainTextMatrixFormatter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlainTextMatrixFormatterTest {

    private MatrixFormatter formatter;
    private Matrix matrix;

    @Before
    public void setup() {
        formatter = new PlainTextMatrixFormatter();
        matrix = new Matrix();
    }

    @Test
    public void formatAnEmptyMatrix() {
        String expected =   "  0 1 2\n" +
                            "0 - - -\n" +
                            "1 - - -\n" +
                            "2 - - -\n";

        assertEquals(expected, formatter.format(matrix));
    }

    @Test
    public void formatAPlayerMarkAsX() {
        String expected =   "  0 1 2\n" +
                            "0 - X -\n" +
                            "1 - - -\n" +
                            "2 - - -\n";

        matrix.playerMark(new Coordinates(1, 0));
        assertEquals(expected, formatter.format(matrix));
    }

    @Test
    public void formatSomePlayerMarks() {
        String expected =   "  0 1 2\n" +
                            "0 - X -\n" +
                            "1 X - -\n" +
                            "2 - X X\n";

        matrix.playerMark(new Coordinates(1, 0));
        matrix.playerMark(new Coordinates(0, 1));
        matrix.playerMark(new Coordinates(1, 2));
        matrix.playerMark(new Coordinates(2, 2));

        assertEquals(expected, formatter.format(matrix));
    }

    @Test
    public void formatSomeComputerMarksAsO() {
        String expected =   "  0 1 2\n" +
                            "0 - - O\n" +
                            "1 - - O\n" +
                            "2 O O -\n";

        matrix.computerMark(new Coordinates(2, 0));
        matrix.computerMark(new Coordinates(2, 1));
        matrix.computerMark(new Coordinates(0, 2));
        matrix.computerMark(new Coordinates(1, 2));

        assertEquals(expected, formatter.format(matrix));
    }

    @Test
    public void formatFullMixedMarkedMatrix() {
        String expected =   "  0 1 2\n" +
                            "0 O O X\n" +
                            "1 X X O\n" +
                            "2 O X X\n";

        matrix.computerMark(new Coordinates(0, 0));
        matrix.computerMark(new Coordinates(1, 0));
          matrix.playerMark(new Coordinates(2, 0));
          matrix.playerMark(new Coordinates(0, 1));
          matrix.playerMark(new Coordinates(1, 1));
        matrix.computerMark(new Coordinates(2, 1));
        matrix.computerMark(new Coordinates(0, 2));
          matrix.playerMark(new Coordinates(1, 2));
          matrix.playerMark(new Coordinates(2, 2));

        assertEquals(expected, formatter.format(matrix));
    }
}
