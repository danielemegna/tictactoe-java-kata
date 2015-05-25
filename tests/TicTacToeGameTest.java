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
    public void inANewGame_CellsAreEmpty() {
        assertCellState(0, 0, CellStateEnum.Empty);
        assertCellState(0, 1, CellStateEnum.Empty);
        assertCellState(1, 0, CellStateEnum.Empty);
        assertCellState(2, 1, CellStateEnum.Empty);
        assertCellState(2, 2, CellStateEnum.Empty);
    }

    @Test
    public void askingCellStateUsingWrongCoordinates_throwsACoordinateOutOfBoundsException()
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
    public void playerCanMarkEmptyCells()
    {
        game.playerMark(0, 1);
        assertCellState(0, 1, CellStateEnum.PlayerMarked);

        game.playerMark(1, 1);
        assertCellState(1, 1, CellStateEnum.PlayerMarked);

        game.playerMark(2, 0);
        assertCellState(2, 0, CellStateEnum.PlayerMarked);
    }

    @Test
    public void playerMarksDoNotAffectOtherCells()
    {
        game.playerMark(0, 1);
        game.playerMark(1, 0);
        game.playerMark(2, 1);
        game.playerMark(2, 2);
        assertCellState(0, 0, CellStateEnum.Empty);
        assertCellState(1, 1, CellStateEnum.Empty);
        assertCellState(1, 2, CellStateEnum.Empty);
    }

    @Test
    public void markingCellsUsingWrongCoordinates_throwsACoordinateOutOfBoundsException()
    {
        try {
            game.playerMark(1, 3);
            fail("Expected CoordinateOutOfBoundsException has not been thrown!");
        } catch (CoordinateOutOfBoundsException ex) { }

        try {
            game.playerMark(5, 0);
            fail("Expected CoordinateOutOfBoundsException has not been thrown!");
        } catch (CoordinateOutOfBoundsException ex) { }

        try {
            game.playerMark(5, 6);
            fail("Expected CoordinateOutOfBoundsException has not been thrown!");
        } catch (CoordinateOutOfBoundsException ex) { }
    }

    @Test
    public void markingTwiceTheSameCell_throwsAnAlreadyMarkedCellAttemptException()
    {
        game.playerMark(0,0);
        try {
            game.playerMark(0,0);
            fail("Expected AlreadyMarkedCellAttemptException has not been thrown!");
        } catch (AlreadyMarkedCellAttemptException ex) { }
    }

    private void assertCellState(int x, int y, CellStateEnum state) {
        assertEquals(state, game.getCellState(x, y));
    }

}
