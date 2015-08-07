package component;

import TicTacToe.Cell.Board;
import TicTacToe.Referee.Referee;
import TicTacToe.Referee.Verdict;
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
    public void givenAnEmptyBoard_refereeSeeNoWinner() {
        assertRefereeVerdict(Verdict.thereIsNoWinner);
    }

    @Test
    public void markingACell_hasNoWinner() {
        helper.playerMark(0, 0);
        assertRefereeVerdict(Verdict.thereIsNoWinner);
    }

    @Test
    public void markingEveryCellWithoutWinner() {
        helper.markBoardFromString(
            "OXX" +
            "XOO" +
            "XOX"
        );

        assertRefereeVerdict(Verdict.thereIsNoWinner);
    }

    @Test
    public void markingARow_CausesAWinner() {
        helper.markBoardFromString(
            "XXX" +
            " OO" +
            "   "
        );

        assertRefereeVerdict(Verdict.playerIsTheWinner);
    }

    @Test
    public void markingAColumn_CausesAWinner() {
        helper.markBoardFromString(
            "XOX" +
            " OX" +
            " O "
        );
        assertRefereeVerdict(Verdict.computerIsTheWinner);
    }

    @Test
    public void markingACross_CausesAWinner() {
        helper.markBoardFromString(
            "XOO" +
            " XO" +
            "  X"
        );
        assertRefereeVerdict(Verdict.playerIsTheWinner);
    }

    private void assertRefereeVerdict(Verdict expected) {
        Verdict v = referee.generateRefereeVerdict(board);
        assertEquals(expected, v);
    }
}
