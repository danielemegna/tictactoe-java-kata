package unit;

import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Game.Game;
import helpers.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private SpyDisplay spyDisplay;
    private SpyPlayerFactory spyPlayerFactory;
    private SpyReferee spyReferee;
    private SpyBoard spyBoard;
    private SpyPlayer firstSpyPlayer;
    private SpyPlayer secondSpyPlayer;
    private Game game;

    @Before
    public void setup() {
        spyDisplay = new SpyDisplay();
        spyReferee = new SpyReferee();
        spyBoard = new SpyBoard();
        firstSpyPlayer = new SpyPlayer(CellMarkSign.Cross);
        secondSpyPlayer = new SpyPlayer(CellMarkSign.Circle);
        spyPlayerFactory = new SpyPlayerFactory(firstSpyPlayer, secondSpyPlayer);
    }

    @Test
    public void callsAfterInit() {
        startSpiesCallsRegistrations();

        instantiateGame();

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
        instantiateGame();
        startSpiesCallsRegistrations();

        game.play();

        assertEquals(Arrays.asList(
            "printBoard(class helpers.SpyBoard)",
            "printBoard(class helpers.SpyBoard)",
            "announceTie()",
            "shutDownMessage()"
        ), spyDisplay.calls());

        assertEquals(Arrays.asList(
            "doNextMove(class helpers.SpyBoard)"
        ), firstSpyPlayer.calls());

        assertEquals(Arrays.asList(
            "isFull()"
        ), spyBoard.calls());

        assertEquals(Arrays.asList(
            "getWinnerCellMark(class helpers.SpyBoard)"
        ), spyReferee.calls());
    }

    private void instantiateGame() {
        game = new Game(spyDisplay, spyPlayerFactory, spyReferee, spyBoard);
    }

    private void startSpiesCallsRegistrations() {
        spyDisplay.startRegistration();
        spyReferee.startRegistration();
        spyBoard.startRegistration();
        firstSpyPlayer.startRegistration();
        secondSpyPlayer.startRegistration();
        spyPlayerFactory.startRegistration();
    }
}
