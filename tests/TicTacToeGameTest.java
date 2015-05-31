import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class TicTacToeGameTest {

    private TicTacToeGame game;

    @Before
    public void setup() {
        this.game = new TicTacToeGame("Player name");
    }

    @Test
    public void someAssertsOnNewGame() {
        assertFalse(game.thereIsAWinner());
        assertEquals("Player name", game.getPlayerName());
    }

    @Test
    public void markWithWrongCoordinates_throwsACoordinateOutOfBoundsException()
    {
        String failMessage = "Expected CoordinateOutOfBoundsException not catched";

        try { game.playerMark(0, 3); fail(failMessage); }
        catch(Coordinates.CoordinateOutOfBoundsException ex) {}
        try { game.computerMark(5, 1); fail(failMessage); }
        catch(Coordinates.CoordinateOutOfBoundsException ex) {}
        try { game.playerMark(42, 13); fail(failMessage); }
        catch(Coordinates.CoordinateOutOfBoundsException ex) {}
    }

    @Test
    public void markingARow_CausesAWinner() {
        game.playerMark(0, 0);
        game.playerMark(1, 0);
        game.playerMark(2, 0);
        assertTrue("Fail asserting markingARow_CausesAWinner", game.thereIsAWinner());
        assertTrue("Fail asserting playerWon", game.playerWon());
        assertFalse("Fail asserting playerWon", game.computerWon());

        setup();

        game.computerMark(0, 1);
        game.computerMark(1, 1);
        game.computerMark(2, 1);
        assertTrue("Fail asserting markingARow_CausesAWinner", game.thereIsAWinner());
        assertTrue("Fail asserting playerWon", game.computerWon());
        assertFalse("Fail asserting playerWon", game.playerWon());
    }

    @Test
    public void markingAColumn_CausesAWinner() {
        game.computerMark(0, 0);
        game.computerMark(0, 1);
        game.computerMark(0, 2);
        assertTrue("Fail asserting markingAColumn_CausesAWinner", game.thereIsAWinner());
        assertFalse("Fail asserting playerWon", game.playerWon());
        assertTrue("Fail asserting playerWon", game.computerWon());

        setup();

        game.playerMark(2, 0);
        game.playerMark(2, 1);
        game.playerMark(2, 2);
        assertTrue("Fail asserting markingAColumn_CausesAWinner", game.thereIsAWinner());
        assertTrue("Fail asserting playerWon", game.playerWon());
        assertFalse("Fail asserting playerWon", game.computerWon());
    }

    @Test
    public void markingACross_CausesAWinner() {
        game.computerMark(0, 0);
        game.computerMark(1, 1);
        game.computerMark(2, 2);
        assertTrue("Fail asserting markingACross_CausesAWinner", game.thereIsAWinner());
        assertTrue("Fail asserting playerWon", game.computerWon());
        assertFalse("Fail asserting playerWon", game.playerWon());

        setup();

        game.playerMark(0, 0);
        game.playerMark(1, 1);
        game.playerMark(2, 2);
        assertTrue("Fail asserting markingACross_CausesAWinner", game.thereIsAWinner());
        assertFalse("Fail asserting playerWon", game.computerWon());
        assertTrue("Fail asserting playerWon", game.playerWon());
    }


}
