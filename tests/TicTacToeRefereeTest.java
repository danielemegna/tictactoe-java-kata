import Coordinates.TicTacToeCoordinates;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TicTacToeRefereeTest {

    @Test
    public void givenAnEmptyMatrix_refereeSeeNoWinner() {
        TicTacToeReferee r = new TicTacToeReferee();
        TicTacToeReferee.RefereeVerdict rv = r.generateRefereeVerdict(new CellMatrix());

        assertFalse(rv.thereIsAWinner());
        assertFalse(rv.playerIsTheWinner());
        assertFalse(rv.computerIsTheWinner());
    }

    @Test
    public void aMatrixWithAMarkedRow_hasAWinner() {
        TicTacToeReferee r = new TicTacToeReferee();

        CellMatrix m = new CellMatrix();
        m.playerMark(new TicTacToeCoordinates(0,0));
        m.playerMark(new TicTacToeCoordinates(0,1));
        m.playerMark(new TicTacToeCoordinates(0,2));


        TicTacToeReferee.RefereeVerdict rv = r.generateRefereeVerdict(m);

        assertTrue(rv.thereIsAWinner());
        assertTrue(rv.playerIsTheWinner());
        assertFalse(rv.computerIsTheWinner());
    }
}
