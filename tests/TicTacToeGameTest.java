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
    public void inANewGame_CellsAreEmpty() {
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                assertCellState(i, j, CellStateEnum.Empty);
    }

    @Test
    public void askingCellStateUsingWrongCoordinates_throwsACoordinateOutOfBoundsException() {
        String failMessage = "Expected CoordinateOutOfBoundsException has not been thrown!";
        try { game.getCellState(0, 3); fail(failMessage); }
        catch (CoordinateOutOfBoundsException ex) { }
        try { game.getCellState(4, 1); fail(failMessage); }
        catch (CoordinateOutOfBoundsException ex) { }
        try { game.getCellState(4, 7); fail(failMessage); }
        catch (CoordinateOutOfBoundsException ex) { }
    }

    @Test
    public void playerCanMarkEmptyCells() {
        game.playerMark(0, 1);
        assertCellState(0, 1, CellStateEnum.PlayerMarked);
        game.playerMark(1, 1);
        assertCellState(1, 1, CellStateEnum.PlayerMarked);
    }

    @Test
    public void computerCanMarkEmptyCells() {
        game.computerMark(0, 1);
        assertCellState(0, 1, CellStateEnum.ComputerMarked);
        game.computerMark(2, 1);
        assertCellState(2, 1, CellStateEnum.ComputerMarked);
    }

    @Test
    public void marksDoNotAffectOtherCells() {
        game.playerMark(0, 1);
        game.computerMark(1, 0);
        game.playerMark(2, 1);
        game.computerMark(2, 2);
        assertCellState(0, 0, CellStateEnum.Empty);
        assertCellState(1, 1, CellStateEnum.Empty);
        assertCellState(1, 2, CellStateEnum.Empty);
    }

    private void assertCellState(int x, int y, CellStateEnum expected) {
        CellStateEnum actual = game.getCellState(x, y);
        assertEquals(
                "Fail asserting cell [" + x + "][" + y + "] state." +
                        "Actual [" + actual + "], expected [" + expected + "]",
                expected, actual
        );
    }

    @Test
    public void markingCellsUsingWrongCoordinates_throwsACoordinateOutOfBoundsException() {
        String failMessage = "Expected CoordinateOutOfBoundsException has not been thrown!";
        try { game.playerMark(1, 3); fail(failMessage); }
        catch (CoordinateOutOfBoundsException ex) { }
        try { game.computerMark(5, 0); fail(failMessage); }
        catch (CoordinateOutOfBoundsException ex) { }
        try { game.playerMark(5, 6); fail(failMessage); }
        catch (CoordinateOutOfBoundsException ex) { }
    }

    @Test
    public void markingTwiceTheSameCell_throwsAnAlreadyMarkedCellAttemptException() {
        String failMessage = "Expected AlreadyMarkedCellAttemptException has not been thrown!";

        game.playerMark(0, 0);
        game.computerMark(1, 1);

        try { game.playerMark(0, 0); fail(failMessage); }
        catch (AlreadyMarkedCellAttemptException ex) { }
        try { game.computerMark(1, 1); fail(failMessage); }
        catch (AlreadyMarkedCellAttemptException ex) { }
        try { game.playerMark(1, 1); fail(failMessage); }
        catch (AlreadyMarkedCellAttemptException ex) { }
        try { game.computerMark(0, 0); fail(failMessage); }
        catch (AlreadyMarkedCellAttemptException ex) { }
    }

    @Test
    public void markingARow_CausesAWinner() {
        game.playerMark(0, 0);
        game.playerMark(1, 0);
        game.playerMark(2, 0);
        assertTrue("Fail asserting markingARow_CausesAWinner", game.thereIsAWinner());

        game.reset();

        game.computerMark(0, 1);
        game.computerMark(1, 1);
        game.computerMark(2, 1);
        assertTrue("Fail asserting markingARow_CausesAWinner", game.thereIsAWinner());
    }

    @Test
    public void markingAColumn_CausesAWinner() {
        game.playerMark(0, 0);
        game.playerMark(0, 1);
        game.playerMark(0, 2);

        assertTrue("Fail asserting markingARow_CausesAWinner", game.thereIsAWinner());
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
        assertCellState(1, 0, CellStateEnum.Empty);
        assertCellState(2, 0, CellStateEnum.Empty);
        assertCellState(0, 0, CellStateEnum.Empty);
        assertCellState(0, 2, CellStateEnum.Empty);
    }


}
