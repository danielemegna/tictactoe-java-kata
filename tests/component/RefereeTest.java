package component;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Referee.Referee;
import helpers.BoardTestHelper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RefereeTest {

    private Board board;
    private Referee referee;
    private BoardTestHelper helper;

    @Before
    public void setup() {
        referee = new Referee();
        board = new Board();
        helper = new BoardTestHelper(board);
    }

    @Test
    public void emptyBoard_hasNoWinner() {
        assertVerdictNoWinner();
    }

    @Test
    public void markingACell_hasNoWinner() {
        helper.mark(0, 0, CellMarkSign.Cross);
        assertVerdictNoWinner();
    }

    @Test
    public void markingEveryCellWithoutWinner() {
        helper.markBoardFromString(
            "OXX" +
            "XOO" +
            "XOX"
        );

        assertVerdictNoWinner();
    }

    @Test
    public void markingARow_CausesAWinner() {
        helper.markBoardFromString(
            "XXX" +
            " OO" +
            "   "
        );

        assertWinnerMarkSign(CellMarkSign.Cross);
    }

    @Test
    public void markingAColumn_CausesAWinner() {
        helper.markBoardFromString(
            "XOX" +
            " OX" +
            " O "
        );
        assertWinnerMarkSign(CellMarkSign.Circle);
    }

    @Test
    public void markingADiagonal_CausesAWinner() {
        helper.markBoardFromString(
            "XOO" +
            " XO" +
            "  X"
        );
        assertWinnerMarkSign(CellMarkSign.Cross);
    }

    private void assertVerdictNoWinner() {
        CellMarkSign winnerCellMark = referee.getWinnerCellMark(board);
        assertNull(winnerCellMark);
    }

    private void assertWinnerMarkSign(CellMarkSign sign) {
        CellMarkSign winnerCellMark = referee.getWinnerCellMark(board);
        assertEquals(sign, winnerCellMark);
    }

}
