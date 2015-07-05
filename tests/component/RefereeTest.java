package component;

import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Cell.Board;
import TicTacToe.Referee.Referee;
import TicTacToe.Referee.Verdict;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RefereeTest {

    private Board board;
    private Referee referee;
    private Verdict verdict;

    @Before
    public void setup() {
        referee = new Referee();
        board = new Board();
    }

    @Test
    public void givenAnEmptyBoard_refereeSeeNoWinner() {
        assertRefereeVerdict(false, false);
    }

    @Test
    public void markingACell_hasNoWinner() {
        playerMark(0, 0);
        assertRefereeVerdict(false, false);
    }

    @Test
    public void markingEveryCellWithoutWinner() {
        computerMark(0, 0);
          playerMark(1, 0);
          playerMark(2, 0);
          playerMark(0, 1);
        computerMark(1, 1);
        computerMark(2, 1);
          playerMark(0, 2);
        computerMark(1, 2);
          playerMark(2, 2);

        assertRefereeVerdict(false, false);
    }

    @Test
    public void markingARow_CausesAWinner() {
        playerMark(0, 0);
        playerMark(1, 0);
        playerMark(2, 0);
        assertRefereeVerdict(true, false);

        setup();

        computerMark(0, 1);
        computerMark(1, 1);
        computerMark(2, 1);
        assertRefereeVerdict(false, true);
    }

    @Test
    public void markingAColumn_CausesAWinner() {
        computerMark(0, 0);
        computerMark(0, 1);
        computerMark(0, 2);
        assertRefereeVerdict(false, true);

        setup();

        playerMark(2, 0);
        playerMark(2, 1);
        playerMark(2, 2);
        assertRefereeVerdict(true, false);
    }

    @Test
    public void markingACross_CausesAWinner() {
        computerMark(0, 0);
        computerMark(1, 1);
        computerMark(2, 2);
        assertRefereeVerdict(false, true);

        setup();

        playerMark(0, 0);
        playerMark(1, 1);
        playerMark(2, 2);
        assertRefereeVerdict(true, false);
    }

    private void playerMark(int x, int y) {
        board.playerMark(new Coordinates(x, y));
    }

    private void computerMark(int x, int y) {
        board.computerMark(new Coordinates(x, y));
    }

    private void assertRefereeVerdict(boolean playerIsTheWinner, boolean computerIsTheWinner) {
        verdict = referee.generateRefereeVerdict(board);
        assertEquals(playerIsTheWinner, verdict.playerIsTheWinner());
        assertEquals(computerIsTheWinner, verdict.computerIsTheWinner());
    }
}
