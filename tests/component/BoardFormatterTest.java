package component;

import TicTacToe.Cell.Board;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Display.BoardFormatter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardFormatterTest {

    private BoardFormatter formatter;
    private Board board;

    @Before
    public void setup() {
        formatter = new BoardFormatter();
        board = new Board();
    }

    @Test
    public void formatAnEmptyBoard() {
        String expected =   "  0 1 2\n" +
                            "0 - - -\n" +
                            "1 - - -\n" +
                            "2 - - -\n";

        assertEquals(expected, formatter.format(board));
    }

    @Test
    public void formatAPlayerMarkAsX() {
        String expected =   "  0 1 2\n" +
                            "0 - X -\n" +
                            "1 - - -\n" +
                            "2 - - -\n";

        board.playerMark(new Coordinates(1, 0));
        assertEquals(expected, formatter.format(board));
    }

    @Test
    public void formatSomePlayerMarks() {
        String expected =   "  0 1 2\n" +
                            "0 - X -\n" +
                            "1 X - -\n" +
                            "2 - X X\n";

        board.playerMark(new Coordinates(1, 0));
        board.playerMark(new Coordinates(0, 1));
        board.playerMark(new Coordinates(1, 2));
        board.playerMark(new Coordinates(2, 2));

        assertEquals(expected, formatter.format(board));
    }

    @Test
    public void formatSomeComputerMarksAsO() {
        String expected =   "  0 1 2\n" +
                            "0 - - O\n" +
                            "1 - - O\n" +
                            "2 O O -\n";

        board.computerMark(new Coordinates(2, 0));
        board.computerMark(new Coordinates(2, 1));
        board.computerMark(new Coordinates(0, 2));
        board.computerMark(new Coordinates(1, 2));

        assertEquals(expected, formatter.format(board));
    }

    @Test
    public void formatFullMixedMarkedBoard() {
        String expected =   "  0 1 2\n" +
                            "0 O O X\n" +
                            "1 X X O\n" +
                            "2 O X X\n";

        board.computerMark(new Coordinates(0, 0));
        board.computerMark(new Coordinates(1, 0));
          board.playerMark(new Coordinates(2, 0));
          board.playerMark(new Coordinates(0, 1));
          board.playerMark(new Coordinates(1, 1));
        board.computerMark(new Coordinates(2, 1));
        board.computerMark(new Coordinates(0, 2));
          board.playerMark(new Coordinates(1, 2));
          board.playerMark(new Coordinates(2, 2));

        assertEquals(expected, formatter.format(board));
    }
}
