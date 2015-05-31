import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class TicTacToeRefereeTest {

    @Test
    public void givenAnEmptyMatrix_refereeSeeNoWinner() {
        TicTacToeReferee r = new TicTacToeReferee(new CellMatrix());
        assertFalse(r.thereIsAWinner());
        assertFalse(r.playerIsTheWinner());
        assertFalse(r.computerIsTheWinner());
    }
}
