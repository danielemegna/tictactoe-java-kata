import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

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

        game.playerMark(2, 0);
        assertCellState(2, 0, CellStateEnum.PlayerMarked);
    }

    @Test
    public void playerMarksDoNotAffectOtherCells() {
        game.playerMark(0, 1);
        game.playerMark(1, 0);
        game.playerMark(2, 1);
        game.playerMark(2, 2);
        assertCellState(0, 0, CellStateEnum.Empty);
        assertCellState(1, 1, CellStateEnum.Empty);
        assertCellState(1, 2, CellStateEnum.Empty);
    }

    @Test
    public void markingCellsUsingWrongCoordinates_throwsACoordinateOutOfBoundsException() {
        String failMessage = "Expected CoordinateOutOfBoundsException has not been thrown!";

        try { game.playerMark(1, 3); fail(failMessage); }
        catch (CoordinateOutOfBoundsException ex) { }

        try { game.playerMark(5, 0); fail(failMessage); }
        catch (CoordinateOutOfBoundsException ex) { }

        try { game.playerMark(5, 6); fail(failMessage); }
        catch (CoordinateOutOfBoundsException ex) { }
    }

    @Test
    public void markingTwiceTheSameCell_throwsAnAlreadyMarkedCellAttemptException() {
        String failMessage = "Expected AlreadyMarkedCellAttemptException has not been thrown!";
        game.playerMark(0,0);
        try { game.playerMark(0,0); fail(failMessage); }
        catch (AlreadyMarkedCellAttemptException ex) { }
    }

    @Test
    public void computerCanMarkEmptyCells() {
        game.computerMark(0, 1);
        assertCellState(0, 1, CellStateEnum.ComputerMarked);
    }

    @Test
    public void playerCannotMarkAComputerMarkedCell() {
        String failMessage = "Expected AlreadyMarkedCellAttemptException has not been thrown!";
        game.computerMark(0, 1);

        try { game.playerMark(0, 1); fail(failMessage); }
        catch (AlreadyMarkedCellAttemptException ex) { }
    }

    private void assertCellState(int x, int y, CellStateEnum expected) {
        CellStateEnum actual = game.getCellState(x, y);
        assertEquals(
            "Fail asserting cell ["+ x + "][" + y + "] state." +
            "Actual [" + actual + "], expected [" + expected + "]",
            expected, actual
        );
    }

}
