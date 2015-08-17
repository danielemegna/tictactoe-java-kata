package component;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Game.Referee;
import helpers.BoardTestHelper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RefereeTest {

    private Referee referee;
    private Board board;
    private BoardTestHelper helper;

    @Before
    public void setup() {
        referee = new Referee();
        board = new Board();
        helper = new BoardTestHelper(board);
    }

    @Test
    public void emptyBoardHasNoWinner() {
        assertVerdictNoWinner();
    }

    @Test
    public void oneCellMarkedBoard() {
        helper.mark(0, 0, CellMarkSign.Cross);
        assertVerdictNoWinner();
    }

    @Test
    public void fullBoardWithoutWinner() {
        helper.markBoardFromString(
            "OXX" +
            "XOO" +
            "XOX"
        );

        assertVerdictNoWinner();
    }

    @Test
    public void fullRowWinner() {
        helper.markBoardFromString(
            "XXX" +
            " OO" +
            "   "
        );

        assertWinnerMarkSign(CellMarkSign.Cross);
    }

    @Test
    public void fullColumnWinner() {
        helper.markBoardFromString(
            "XOX" +
            " OX" +
            " O "
        );
        assertWinnerMarkSign(CellMarkSign.Circle);
    }

    @Test
    public void fullDiagonalWinner() {
        helper.markBoardFromString(
            "XOO" +
            " XO" +
            "  X"
        );
        assertWinnerMarkSign(CellMarkSign.Cross);
    }

    private void assertVerdictNoWinner() {
        assertWinnerMarkSign(null);
    }

    private void assertWinnerMarkSign(CellMarkSign sign) {
        CellMarkSign winnerCellMark = referee.getWinnerCellMark(board);
        assertEquals(sign, winnerCellMark);
    }

}
