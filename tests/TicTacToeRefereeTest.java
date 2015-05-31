import Coordinates.TicTacToeCoordinates;
import TicTacToeCell.Cell;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    public void aMatrixWithAMarkedRow_hasAWinner() {
        playerMark(0, 0);
        playerMark(0, 1);
        playerMark(0, 2);

        assertRefereeVerdict(true, false);
    }

    private void playerMark(int x, int y) {
        matrix.playerMark(new TicTacToeCoordinates(x, y));
    }

    private void assertRefereeVerdict(boolean playerIsTheWinner, boolean computerIsTheWinner) {
        verdict = referee.generateRefereeVerdict(matrix);
        assertEquals(playerIsTheWinner, verdict.playerIsTheWinner());
        assertEquals(computerIsTheWinner, verdict.computerIsTheWinner());
    }
}
