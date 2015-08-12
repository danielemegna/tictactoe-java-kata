package unit;

import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Game.Game;
import helpers.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        firstSpyPlayer = new SpyPlayer(CellMarkSign.Cross, "firstSpyPlayer");
        secondSpyPlayer = new SpyPlayer(CellMarkSign.Circle, "secondSpyPlayer");
        spyPlayerFactory = new SpyPlayerFactory(firstSpyPlayer, secondSpyPlayer);
    }

    @Test
    public void callsAfterInit() {
        activateAllSpies();
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
    public void tieInOneMove() {
        instantiateGame();
        activateAllSpies();
        spyBoard.setFullAfter(1);
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
        assertEquals(0, secondSpyPlayer.calls().size());

        assertEquals(Arrays.asList(
            "isFull()"
        ), spyBoard.calls());

        assertEquals(Arrays.asList(
            "getWinnerCellMark(class helpers.SpyBoard)"
        ), spyReferee.calls());
    }

    @Test
    public void winnerInOneMove() {
        instantiateGame();
        activateAllSpies();
        spyReferee.setWinner(CellMarkSign.Cross);
        spyReferee.setWinnerAfter(1);

        game.play();

        assertTrue(spyDisplay.calls().contains(
            "announceWinner name firstSpyPlayer"
        ));

        assertEquals(Arrays.asList(
            "doNextMove(class helpers.SpyBoard)",
            "getName()"
        ), firstSpyPlayer.calls());

        assertEquals(Arrays.asList(
            "getWinnerCellMark(class helpers.SpyBoard)"
        ), spyReferee.calls());
    }

    @Test
    public void winnerAfterSomeMoves() {
        instantiateGame();
        activateAllSpies();
        spyReferee.setWinner(CellMarkSign.Circle);
        spyReferee.setWinnerAfter(4);

        game.play();

        assertTrue(spyDisplay.calls().contains(
            "announceWinner name secondSpyPlayer"
        ));

        assertEquals(Arrays.asList(
            "doNextMove(class helpers.SpyBoard)",
            "doNextMove(class helpers.SpyBoard)"
        ), firstSpyPlayer.calls());

        assertEquals(Arrays.asList(
            "doNextMove(class helpers.SpyBoard)",
            "doNextMove(class helpers.SpyBoard)",
            "getName()"
        ), secondSpyPlayer.calls());
    }

    private void instantiateGame() {
        game = new Game(spyDisplay, spyPlayerFactory, spyReferee, spyBoard);
    }

    private void activateAllSpies() {
        spyDisplay.activateSpy();
        spyReferee.activateSpy();
        spyBoard.activateSpy();
        firstSpyPlayer.activateSpy();
        secondSpyPlayer.activateSpy();
        spyPlayerFactory.activateSpy();
    }
}
