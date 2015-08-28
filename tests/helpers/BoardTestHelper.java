package helpers;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.Coordinates;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardTestHelper {

    private Board board;

    public BoardTestHelper(Board b) {
        this.board = b;
    }

    public void mark(int x, int y, CellMarkSign sign) {
        board.mark(new Coordinates(x, y), sign);
    }

    public void assertCellIsEmpty(int x, int y) {
        Coordinates c = new Coordinates(x, y);
        assertTrue("Cell " + c + " is not empty like expected", board.isCellEmpty(c));
    }

    public void assertCellSign(int x, int y, CellMarkSign expected) {
        Coordinates c = new Coordinates(x, y);
        CellMarkSign actual = board.getCellSign(c).orElse(null);
        assertEquals(
            "Fail asserting cell sign at coordinates " + c + ". " +
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
                        mark(x, y, CellMarkSign.Cross);
                        break;
                    case 'O':
                        mark(x, y, CellMarkSign.Circle);
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
