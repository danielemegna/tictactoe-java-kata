import Coordinates.TicTacToeCoordinates;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TicTacToeRefereeTest {

    @Test
    public void givenAnEmptyMatrix_refereeSeeNoWinner() {
        TicTacToeReferee r = new TicTacToeReferee(new CellMatrix());
        assertFalse(r.thereIsAWinner());
        assertFalse(r.playerIsTheWinner());
        assertFalse(r.computerIsTheWinner());
    }

    @Test
    public void aMatrixWithAMarkedRow_hasAWinner() {
        CellMatrix m = new CellMatrix();
        m.playerMark(new TicTacToeCoordinates(0,0));
        m.playerMark(new TicTacToeCoordinates(0,1));
        m.playerMark(new TicTacToeCoordinates(0,2));

        TicTacToeReferee r = new TicTacToeReferee(m);
        assertTrue(r.thereIsAWinner());
        assertTrue(r.playerIsTheWinner());
        assertFalse(r.computerIsTheWinner());
    }
}
