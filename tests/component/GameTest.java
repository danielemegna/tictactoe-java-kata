package component;

import TicTacToe.Game.Game;
import helpers.SpyBoard;
import helpers.SpyDisplay;
import helpers.SpyPlayerFactory;
import helpers.SpyReferee;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private SpyDisplay spyDisplay;
    private SpyPlayerFactory spyPlayerFactory;
    private SpyReferee spyReferee;
    private SpyBoard spyBoard;
    private Game game;

    @Before
    public void setup() {
        spyDisplay = new SpyDisplay();
        spyPlayerFactory = new SpyPlayerFactory();
        spyReferee = new SpyReferee();
        spyBoard = new SpyBoard();

        game = instantiateWithSpies();
    }

    @Test
    public void callsAfterInit() {
        assertEquals(Arrays.asList(
            "welcomeMessage()",
            "askForGameMode()"
        ), spyDisplay.calls());

        assertEquals(Arrays.asList(
            "listFromGameMode(HumanVsComputer)"
        ), spyPlayerFactory.calls());
    }

    @Test
    public void callsOnPlay() {
        /*game.play();

        assertEquals(Arrays.asList(
            "welcomeMessage()",
            "askForGameMode()"
        ), spyDisplay.calls());

        assertEquals(Arrays.asList(
            "listFromGameMode(HumanVsComputer)"
        ), spyPlayerFactory.calls());*/
    }

    private Game instantiateWithSpies() {
        return new Game(spyDisplay, spyPlayerFactory, spyReferee, spyBoard);
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
