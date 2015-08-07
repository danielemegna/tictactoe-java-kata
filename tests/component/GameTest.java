package component;

import TicTacToe.Game;
import TicTacToe.GameTurnException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    @Before
    public void setup() {
        this.game = new Game("Player name", true, null, null);
    }

    @Test
    public void gameProvidesPlayerName() {
        assertEquals("Player name", game.getPlayerName());
    }

    @Test
    public void newGameHasNoWinner() {
        assertFalse(game.computerWon());
        assertFalse(game.playerWon());
    }

    @Test
    public void inANewGameBoardIsNotFull() {
        assertFalse(game.isBoardFull());
    }

    @Test(expected = TicTacToe.Coordinates.CoordinateOutOfBoundsException.class)
    public void markWithWrongCoordinates_throwsAnException() {
        game.playerMark(0, 3);
    }

    @Test(expected = GameTurnException.class)
    public void playersCannotMarkTwice_itsOtherPlayerTurn() {
        game.playerMark(0, 0);
        game.playerMark(0, 1);
    }

    // TO BE REPLACED BY TESTS ON GAME - BOARD MESSAGES EXCHANGE
    /*@Test
    public void markingARow_CausesAWinner() {
          game.playerMark(0, 0);
        game.computerMark(0, 1);
          game.playerMark(1, 0);
        game.computerMark(0, 2);
          game.playerMark(2, 0);
        assertTrue("Fail asserting playerWon", game.playerWon());
        assertFalse("Fail asserting playerWon", game.computerWon());

        setup();

          game.playerMark(0, 0);
        game.computerMark(0, 1);
          game.playerMark(1, 2);
        game.computerMark(1, 1);
          game.playerMark(2, 2);
        game.computerMark(2, 1);
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
        assertFalse("Fail asserting playerWon", game.playerWon());
        assertTrue("Fail asserting playerWon", game.computerWon());

        setup();

          game.playerMark(2, 0);
        game.computerMark(0, 0);
          game.playerMark(2, 1);
        game.computerMark(0, 1);
          game.playerMark(2, 2);
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
        assertTrue("Fail asserting playerWon", game.computerWon());
        assertFalse("Fail asserting playerWon", game.playerWon());

        setup();

          game.playerMark(2, 0);
        game.computerMark(2, 1);
          game.playerMark(1, 1);
        game.computerMark(2, 2);
          game.playerMark(0, 2);
        assertFalse("Fail asserting playerWon", game.computerWon());
        assertTrue("Fail asserting playerWon", game.playerWon());
    }

    @Test
    public void markingEveryCellWithoutWinnerTestCase() {
          game.playerMark(1, 0);
        game.computerMark(0, 0);
          game.playerMark(2, 0);
        game.computerMark(1, 1);
          game.playerMark(0, 1);
        game.computerMark(2, 1);
          game.playerMark(0, 2);
        game.computerMark(1, 2);
          game.playerMark(2, 2);

        assertFalse("Fail asserting playerWon", game.computerWon());
        assertFalse("Fail asserting playerWon", game.playerWon());
        assertTrue("Fail asserting game is finished", game.isBoardFull());
    }*/
}
