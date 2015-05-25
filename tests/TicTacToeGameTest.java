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
        assertCellState(0, 0, CellStateEnum.Empty);
        assertCellState(0, 1, CellStateEnum.Empty);
        assertCellState(1, 0, CellStateEnum.Empty);
        assertCellState(2, 1, CellStateEnum.Empty);
        assertCellState(2, 2, CellStateEnum.Empty);
    }

    @Test
    public void askingForWrongCoordinates_throwsACoordinateOutOfBoundsException()
    {
        try {
            game.getCellState(0, 3);
            fail("Expected CoordinateOutOfBoundsException has not been thrown!");
        } catch (CoordinateOutOfBoundsException ex) { }

        try {
            game.getCellState(4, 1);
            fail("Expected CoordinateOutOfBoundsException has not been thrown!");
        } catch (CoordinateOutOfBoundsException ex) { }

        try {
            game.getCellState(4, 7);
            fail("Expected CoordinateOutOfBoundsException has not been thrown!");
        } catch (CoordinateOutOfBoundsException ex) { }

    }

    @Test
    public void playerCanMarkAnEmptyCell()
    {
        game.playerMark(0, 1);
        assertCellState(0, 1, CellStateEnum.PlayerMarked);
    }

    private void assertCellState(int x, int y, CellStateEnum state) {
        assertEquals(state, game.getCellState(x, y));
    }

}
