package unit;

import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Game.Game;
import helpers.*;
import org.junit.Before;
import org.junit.Test;

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
        spyDisplay          = new SpyDisplay();
        spyReferee          = new SpyReferee();
        spyBoard            = new SpyBoard();
        firstSpyPlayer      = new SpyPlayer(CellMarkSign.Cross, "firstSpyPlayer");
        secondSpyPlayer     = new SpyPlayer(CellMarkSign.Circle, "secondSpyPlayer");
        spyPlayerFactory    = new SpyPlayerFactory(firstSpyPlayer, secondSpyPlayer);
        game                = new Game(spyBoard, spyPlayerFactory, spyDisplay, spyReferee);

        activateAllSpies();
    }

    @Test
    public void welcomeAndGameModeOnInit() {
        game = new Game(spyBoard, spyPlayerFactory, spyDisplay, spyReferee);

        SpyHelper.assertCalls(spyDisplay,       "welcomeMessage()", "askForGameMode()");
        SpyHelper.assertCalls(spyPlayerFactory, "listFromGameMode(HumanVsComputer)");
    }

    @Test
    public void tieInOneMove() {
        spyBoard.setFullAfter(0);
        spyBoard.setLastMarkedCoordinates(new Coordinates(0, 1));
        game.play();

        SpyHelper.assertCalls(spyDisplay,
            "printBoard(class helpers.SpyBoard)",
            "coordinatesMarkedMessage([0, 1])",
            "printBoard(class helpers.SpyBoard)",
            "announceTie()",
            "shutDownMessage()"
        );

        SpyHelper.assertCalls(firstSpyPlayer,   "doNextMove(class helpers.SpyBoard)");
        SpyHelper.assertCalls(spyBoard,         "getLastMarkedCoordinates()", "isFull()");
        SpyHelper.assertCallTimes(spyReferee,   "getWinnerCellMark(class helpers.SpyBoard)", 2);

        assertEquals(0, secondSpyPlayer.calls().size());
    }

    @Test
    public void winnerInOneMove() {
        spyReferee.setWinner(CellMarkSign.Cross);
        spyReferee.setWinnerAfter(0);

        game.play();

        SpyHelper.assertCalls(firstSpyPlayer,       "doNextMove(class helpers.SpyBoard)", "getName()");
        SpyHelper.assertCallTimes(spyReferee,       "getWinnerCellMark(class helpers.SpyBoard)", 3);
        SpyHelper.assertCallsContains(spyDisplay,   "announceWinner name firstSpyPlayer");
    }

    @Test
    public void winnerAfterSomeMoves() {
        spyReferee.setWinner(CellMarkSign.Circle);
        spyReferee.setWinnerAfter(3);

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
