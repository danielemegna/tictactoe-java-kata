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
    public void someAssertsOnNewGame()
    {
        assertNotNull(game);
        assertFalse(game.isFull());
        assertFalse(game.thereIsAWinner());
    }

    @Test
    public void inANewGame_everyCellIsEmpty() {
        assertEquals(CellStateEnum.Empty, game.getCellState(0, 0));
        assertEquals(CellStateEnum.Empty, game.getCellState(0, 1));
        assertEquals(CellStateEnum.Empty, game.getCellState(1, 0));
        assertEquals(CellStateEnum.Empty, game.getCellState(2, 1));
        assertEquals(CellStateEnum.Empty, game.getCellState(2, 2));
    }

}
