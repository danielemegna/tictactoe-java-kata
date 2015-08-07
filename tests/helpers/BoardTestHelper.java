package helpers;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellState;
import TicTacToe.Coordinates.Coordinates;

import static org.junit.Assert.assertEquals;

public class BoardTestHelper {

    private Board board;

    public BoardTestHelper(Board b) {
        this.board = b;
    }

    public void playerMark(int x, int y) {
        board.playerMark(new Coordinates(x, y));
    }

    public void computerMark(int x, int y) {
        board.computerMark(new Coordinates(x, y));
    }

    public void assertCellState(int x, int y, CellState expected) {
        Coordinates c = new Coordinates(x, y);
        CellState actual = board.getCellState(c);
        assertEquals(
            "Fail asserting cell state at coordinates [" + c + "] ." +
            "Actual [" + actual + "], expected [" + expected + "]",
            expected, actual
        );
    }

    public void markBoardFromString(String s) {
        int index = 0;
        for(int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                switch(s.charAt(index++)) {
                    case 'X':
                        playerMark(x, y);
                        break;
                    case 'O':
                        computerMark(x, y);
                        break;
                    case ' ':
                        break;
                    default:
                        throw new RuntimeException(
                            "Unexpected char [" + s.charAt(index-1) + "] into markBoardFromString argument"
                        );
                }
            }
        }
    }
}
