import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

/**
 * Created by daniele on 21/05/15.
 */
public class TicTacToeGameTest {

    private TicTacToeGame game;

    @Before
    public void setup()
    {
        this.game = new TicTacToeGame("Player name");
    }

    @Test
    public void startANewGame()
    {
        assertNotNull(game);
    }

    @Test
    public void aNewGameIsNotFull()
    {
        assertFalse(game.isFull());
    }

    @Test
    public void aNewGameHasNoWinnerYet()
    {
        assertFalse(game.thereIsAWinner());
    }
}
