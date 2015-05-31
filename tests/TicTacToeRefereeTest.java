import Coordinates.TicTacToeCoordinates;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TicTacToeRefereeTest {

    private CellMatrix matrix;
    private TicTacToeReferee referee;
    private TicTacToeReferee.RefereeVerdict verdict;

    @Before
    public void setup() {
        referee = new TicTacToeReferee();
        matrix = new CellMatrix();
    }

    @Test
    public void givenAnEmptyMatrix_refereeSeeNoWinner() {
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
        matrix.playerMark(new TicTacToeCoordinates(x, y));
    }

    private void computerMark(int x, int y) {
        matrix.computerMark(new TicTacToeCoordinates(x, y));
    }

    private void assertRefereeVerdict(boolean playerIsTheWinner, boolean computerIsTheWinner) {
        verdict = referee.generateRefereeVerdict(matrix);
        assertEquals(playerIsTheWinner, verdict.playerIsTheWinner());
        assertEquals(computerIsTheWinner, verdict.computerIsTheWinner());
    }
}
