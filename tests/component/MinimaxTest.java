package component;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Player.Minimax;
import TicTacToe.Coordinates.Coordinates;
import helpers.BoardTestHelper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinimaxTest {

    public static final CellMarkSign MY_CELL_SIGN = CellMarkSign.Circle;

    private Minimax minimax;
    private Board board;
    private BoardTestHelper helper;

    @Before
    public void setup() {
        minimax = new Minimax(MY_CELL_SIGN);
        board = new Board();
        helper = new BoardTestHelper(board);
    }

    @Test
    public void lastTieMove_worthZero() {
        helper.markBoardFromString(
            "XOO" +
            "O X" +
            "XXO"
        );

        int lastTieMoveValue = evaluateMove(1, 1);
        assertEquals(0, lastTieMoveValue);
    }

    @Test
    public void myWinnerWithAMove_worthTwo() {
        helper.markBoardFromString(
            "XOX" +
            "XOX" +
            "O  "
        );

        int winMoveValue = evaluateMove(1, 2);
        assertEquals(2, winMoveValue);
    }

    @Test
    public void adversaryCanWinAtTheNextMove_moveValueIsMinusOne() {
        helper.markBoardFromString(
            "X O" +
            "O  " +
            "OXX"
        );

        int adversaryCanWinAtTheNextMove = evaluateMove(1, 0);
        assertEquals(-1, adversaryCanWinAtTheNextMove);
    }

    @Test
    public void everyPossibilityWorthsZero() {
        helper.markBoardFromString(
            "  X" +
            "XOO" +
            "  X"
        );

        assertEquals(0, evaluateMove(0, 0));
        assertEquals(0, evaluateMove(0, 2));
        assertEquals(0, evaluateMove(1, 0));
        assertEquals(0, evaluateMove(1, 2));
    }

    @Test
    public void centerCellIsTheOnlyWayToAvoidDefeat() {
        helper.markBoardFromString(
            "X  " +
            "   " +
            "   "
        );

        assertEquals(0, evaluateMove(1, 1));
        assertEquals(-1, evaluateMove(0, 1));
        assertEquals(-1, evaluateMove(2, 0));
        assertEquals(-1, evaluateMove(2, 2));
    }

    @Test
    public void someMovesLeadMeToSureWin() {
        helper.markBoardFromString(
            "  X" +
            "   " +
            "  O"
        );

        // win moves
        assertEquals(1, evaluateMove(0, 0));
        assertEquals(1, evaluateMove(0, 2));
        assertEquals(1, evaluateMove(1, 2));

        // tie moves
        assertEquals(0, evaluateMove(0, 1));
        assertEquals(0, evaluateMove(1, 0));
        assertEquals(0, evaluateMove(1, 1));

        // defeat move
        assertEquals(-1, evaluateMove(2, 1));
    }

    private int evaluateMove(int x, int y) {
        return minimax.calcolateMoveValue(new Coordinates(x, y), board);
    }


}
