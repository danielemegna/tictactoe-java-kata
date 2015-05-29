import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by daniele on 21/05/15.
 */
public class TicTacToeGameTest {

    private TicTacToeGame game;

    @Before
    public void setup() {
        this.game = new TicTacToeGame("Player name");
    }

    @Test
    public void someAssertsOnNewGame() {
        assertFalse(game.isFull());
        assertFalse(game.thereIsAWinner());
        assertEquals("Player name", game.getPlayerName());
    }



    @Test
    public void gameCanResetCells() {
        game.playerMark(0, 0);
        game.computerMark(0, 1);
        game.playerMark(1, 0);
        game.computerMark(0, 2);
        game.playerMark(2, 0);

        game.reset();

        assertFalse(game.thereIsAWinner());
        assertTrue(game.isCellEmpty(1, 0));
        assertTrue(game.isCellEmpty(2, 0));
        assertTrue(game.isCellEmpty(0, 0));
        assertTrue(game.isCellEmpty(0, 2));
    }

    @Test
    public void markingARow_CausesAWinner() {
        game.playerMark(0, 0);
        game.playerMark(1, 0);
        game.playerMark(2, 0);
        assertTrue("Fail asserting markingARow_CausesAWinner", game.thereIsAWinner());
        assertTrue("Fail asserting playerWon", game.playerWon());
        assertFalse("Fail asserting playerWon", game.computerWon());

        game.reset();

        game.computerMark(0, 1);
        game.computerMark(1, 1);
        game.computerMark(2, 1);
        assertTrue("Fail asserting markingARow_CausesAWinner", game.thereIsAWinner());
        assertFalse("Fail asserting playerWon", game.playerWon());
        assertTrue("Fail asserting playerWon", game.computerWon());

        game.reset();

        game.playerMark(0, 2);
        game.playerMark(1, 2);
        game.playerMark(2, 2);
        assertTrue("Fail asserting markingARow_CausesAWinner", game.thereIsAWinner());
        assertTrue("Fail asserting playerWon", game.playerWon());
        assertFalse("Fail asserting playerWon", game.computerWon());
    }

    @Test
    public void markingAColumn_CausesAWinner() {
        game.playerMark(0, 0);
        game.playerMark(0, 1);
        game.playerMark(0, 2);

        assertTrue("Fail asserting markingARow_CausesAWinner", game.thereIsAWinner());
    }


}
