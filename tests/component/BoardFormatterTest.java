package component;

import TicTacToe.Cell.Board;
import TicTacToe.Display.BoardFormatter;
import helpers.BoardTestHelper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardFormatterTest {

    private BoardFormatter formatter;
    private Board board;
    private BoardTestHelper helper;

    @Before
    public void setup() {
        formatter = new BoardFormatter();
        board = new Board();
        helper = new BoardTestHelper(board);
    }

    @Test
    public void formatAnEmptyBoard() {
        String expected =
            "  0 1 2\n" +
            "0 - - -\n" +
            "1 - - -\n" +
            "2 - - -\n";

        assertEquals(expected, formatter.format(board));
    }

    @Test
    public void formatAPlayerMarkAsX() {
        helper.markBoardFromString(
            " X " +
            "   " +
            "   "
        );

        String expected =
            "  0 1 2\n" +
            "0 - X -\n" +
            "1 - - -\n" +
            "2 - - -\n";

        assertEquals(expected, formatter.format(board));
    }

    @Test
    public void formatSomePlayerMarks() {
        helper.markBoardFromString(
            " X " +
            "X  " +
            " XX"
        );

        String expected =
            "  0 1 2\n" +
            "0 - X -\n" +
            "1 X - -\n" +
            "2 - X X\n";

        assertEquals(expected, formatter.format(board));
    }

    @Test
    public void formatSomeComputerMarksAsO() {
        helper.markBoardFromString(
            "  O" +
            "  O" +
            "OO "
        );

        String expected =
            "  0 1 2\n" +
            "0 - - O\n" +
            "1 - - O\n" +
            "2 O O -\n";

        assertEquals(expected, formatter.format(board));
    }

    @Test
    public void formatFullMixedMarkedBoard() {
        helper.markBoardFromString(
            "OOX" +
            "XXO" +
            "OXX"
        );

        String expected =
            "  0 1 2\n" +
            "0 O O X\n" +
            "1 X X O\n" +
            "2 O X X\n";

        assertEquals(expected, formatter.format(board));
    }
}
