package it;

import TicTacToe.Game;
import TicTacToe.GameTurnException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    @Before
    public void setup() {
        this.game = new Game("Player name");
    }

    @Test
    public void someAssertsOnNewGame() {
        assertFalse(game.thereIsAWinner());
        assertEquals("Player name", game.getPlayerName());
        assertFalse("Fail asserting markingARow_CausesAWinner", game.thereIsAWinner());
        assertFalse("Fail asserting playerWon", game.computerWon());
        assertFalse("Fail asserting playerWon", game.playerWon());
        assertFalse("Fail asserting game is finished", game.isMatrixFull());
    }

    @Test
    public void markWithWrongCoordinates_throwsACoordinateOutOfBoundsException()
    {
        String failMessage = "Expected CoordinateOutOfBoundsException not catched";

        try { game.playerMark(0, 3); fail(failMessage); }
        catch(TicTacToe.Coordinates.CoordinateOutOfBoundsException ex) {}
        game.playerMark(0, 0);

        try { game.computerMark(5, 1); fail(failMessage); }
        catch(TicTacToe.Coordinates.CoordinateOutOfBoundsException ex) {}
        game.computerMark(0, 1);

        try { game.playerMark(42, 13); fail(failMessage); }
        catch(TicTacToe.Coordinates.CoordinateOutOfBoundsException ex) {}
    }

    @Test
    public void markingARow_CausesAWinner() {
        game.playerMark(0, 0);
        game.computerMark(0, 1);
        game.playerMark(1, 0);
        game.computerMark(0, 2);
        game.playerMark(2, 0);
        assertTrue("Fail asserting markingARow_CausesAWinner", game.thereIsAWinner());
        assertTrue("Fail asserting playerWon", game.playerWon());
        assertFalse("Fail asserting playerWon", game.computerWon());

        setup();

        game.playerMark(0, 0);
        game.computerMark(0, 1);
        game.playerMark(1, 2);
        game.computerMark(1, 1);
        game.playerMark(2, 2);
        game.computerMark(2, 1);
        assertTrue("Fail asserting markingARow_CausesAWinner", game.thereIsAWinner());
        assertTrue("Fail asserting playerWon", game.computerWon());
        assertFalse("Fail asserting playerWon", game.playerWon());
    }

    @Test
    public void markingAColumn_CausesAWinner() {
        game.playerMark(1, 0);
        game.computerMark(0, 0);
        game.playerMark(2, 0);
        game.computerMark(0, 1);
        game.playerMark(2, 2);
        game.computerMark(0, 2);
        assertTrue("Fail asserting markingAColumn_CausesAWinner", game.thereIsAWinner());
        assertFalse("Fail asserting playerWon", game.playerWon());
        assertTrue("Fail asserting playerWon", game.computerWon());

        setup();

        game.playerMark(2, 0);
        game.computerMark(0, 0);
        game.playerMark(2, 1);
        game.computerMark(0, 1);
        game.playerMark(2, 2);
        assertTrue("Fail asserting markingAColumn_CausesAWinner", game.thereIsAWinner());
        assertTrue("Fail asserting playerWon", game.playerWon());
        assertFalse("Fail asserting playerWon", game.computerWon());
    }

    @Test
    public void markingACross_CausesAWinner() {
        game.playerMark(0, 1);
        game.computerMark(0, 0);
        game.playerMark(0, 2);
        game.computerMark(1, 1);
        game.playerMark(2, 0);
        game.computerMark(2, 2);
        assertTrue("Fail asserting markingACross_CausesAWinner", game.thereIsAWinner());
        assertTrue("Fail asserting playerWon", game.computerWon());
        assertFalse("Fail asserting playerWon", game.playerWon());

        setup();

        game.playerMark(2, 0);
        game.computerMark(2, 1);
        game.playerMark(1, 1);
        game.computerMark(2, 2);
        game.playerMark(0, 2);
        assertTrue("Fail asserting markingACross_CausesAWinner", game.thereIsAWinner());
        assertFalse("Fail asserting playerWon", game.computerWon());
        assertTrue("Fail asserting playerWon", game.playerWon());
    }

    @Test
    public void markingEveryCellWithoutWinner() {
          game.playerMark(1, 0);
        game.computerMark(0, 0);
          game.playerMark(2, 0);
        game.computerMark(1, 1);
          game.playerMark(0, 1);
        game.computerMark(2, 1);
          game.playerMark(0, 2);
        game.computerMark(1, 2);
          game.playerMark(2, 2);

        assertFalse("Fail asserting markingACross_CausesAWinner", game.thereIsAWinner());
        assertFalse("Fail asserting playerWon", game.computerWon());
        assertFalse("Fail asserting playerWon", game.playerWon());
        assertTrue("Fail asserting game is finished", game.isMatrixFull());
    }

    @Test
    public void playersCannotMarkTwice_itsOtherPlayerTurn() {
        String failMessage = "Expected GameTurnException not catched";

        game.playerMark(0, 0);
        try { game.playerMark(0, 1); fail(failMessage); }
        catch(GameTurnException ex) {}

        game.computerMark(0, 1);
        try { game.computerMark(0, 1); fail(failMessage); }
        catch(GameTurnException ex) {}
    }

}
