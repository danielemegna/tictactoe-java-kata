package component;

import TicTacToe.Game.Game;
import TicTacToe.Game.GameFactory;
import TicTacToe.Player.GameMode;
import helpers.SpyDisplay;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;
    private SpyDisplay spyDisplay;

    @Before
    public void setup() {
        spyDisplay = new SpyDisplay();
        spyDisplay.setNextGameMode(GameMode.HumanVsComputer);
        game = GameFactory.build(spyDisplay);
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
