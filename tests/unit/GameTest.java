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
        spyDisplay          = new SpyDisplay();
        spyReferee          = new SpyReferee();
        spyBoard            = new SpyBoard();
        firstSpyPlayer      = new SpyPlayer(CellMarkSign.Cross, "firstSpyPlayer");
        secondSpyPlayer     = new SpyPlayer(CellMarkSign.Circle, "secondSpyPlayer");
        spyPlayerFactory    = new SpyPlayerFactory(firstSpyPlayer, secondSpyPlayer);
        game                = new Game(spyDisplay, spyPlayerFactory, spyReferee, spyBoard);

        activateAllSpies();
    }

    @Test
    public void welcomeAndGameModeOnInit() {
        game = new Game(spyDisplay, spyPlayerFactory, spyReferee, spyBoard);

        SpyHelper.assertCalls(spyDisplay,
            "welcomeMessage()",
            "askForGameMode()"
        );

        SpyHelper.assertCalls(spyPlayerFactory, "listFromGameMode(HumanVsComputer)");
    }

    @Test
    public void tieInOneMove() {
        spyBoard.setFullAfter(1);
        game.play();

        SpyHelper.assertCalls(spyDisplay,
            "printBoard(class helpers.SpyBoard)",
            "printBoard(class helpers.SpyBoard)",
            "announceTie()",
            "shutDownMessage()"
        );

        SpyHelper.assertCalls(firstSpyPlayer,   "doNextMove(class helpers.SpyBoard)");
        SpyHelper.assertCalls(spyBoard,         "isFull()");
        SpyHelper.assertCalls(spyReferee,       "getWinnerCellMark(class helpers.SpyBoard)");

        assertEquals(0, secondSpyPlayer.calls().size());
    }

    @Test
    public void winnerInOneMove() {
        spyReferee.setWinner(CellMarkSign.Cross);
        spyReferee.setWinnerAfter(1);

        game.play();


        SpyHelper.assertCalls(firstSpyPlayer,
            "doNextMove(class helpers.SpyBoard)",
            "getName()"
        );
        SpyHelper.assertCalls(spyReferee,           "getWinnerCellMark(class helpers.SpyBoard)");
        SpyHelper.assertCallsContains(spyDisplay,   "announceWinner name firstSpyPlayer");
    }

    @Test
    public void winnerAfterSomeMoves() {
        spyReferee.setWinner(CellMarkSign.Circle);
        spyReferee.setWinnerAfter(4);

        game.play();

        SpyHelper.assertCallsContains(spyDisplay, "announceWinner name secondSpyPlayer");
        SpyHelper.assertCalls(firstSpyPlayer,
            "doNextMove(class helpers.SpyBoard)",
            "doNextMove(class helpers.SpyBoard)"
        );
        SpyHelper.assertCalls(secondSpyPlayer,
            "doNextMove(class helpers.SpyBoard)",
            "doNextMove(class helpers.SpyBoard)",
            "getName()"
        );
    }

    private void activateAllSpies() {
        SpyHelper.activate(
            spyDisplay, spyReferee, spyBoard,
            firstSpyPlayer, secondSpyPlayer, spyPlayerFactory
        );
    }
}
