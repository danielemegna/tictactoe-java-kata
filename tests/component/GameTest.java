package component;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Game.Game;
import TicTacToe.Game.PlayersCountException;
import TicTacToe.Player.HumanPlayer;
import TicTacToe.Player.Player;
import TicTacToe.Referee.Referee;
import helpers.SpyBoard;
import helpers.SpyPlayer;
import helpers.SpyDisplay;
import helpers.SpyReferee;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;
    private SpyDisplay spyDisplay;
    private SpyPlayer firstSpyPlayer;
    private SpyPlayer secondSpyPlayer;
    private SpyBoard spyBoard;
    private SpyReferee spyReferee;

    @Before
    public void setup() {
        firstSpyPlayer = new SpyPlayer(CellMarkSign.Cross);
        secondSpyPlayer = new SpyPlayer(CellMarkSign.Circle);
        spyBoard = new SpyBoard();
        spyReferee = new SpyReferee();
        spyDisplay = new SpyDisplay();

        List<Player> players = new ArrayList<Player>() {{
            add(firstSpyPlayer);
            add(secondSpyPlayer);
        }};

        game = new Game(players, spyDisplay, spyBoard, spyReferee);
    }

    @Test
    public void newGame_isNotOver() {
        assertFalse(game.isGameOver());
    }

    @Test
    public void showEmptyBoard() {
        game.showUpdatedBoard();

        assertEquals(
            "printBoard(nullnullnullnullnullnullnullnullnull)",
            spyDisplay.getLastCalledMethod()
        );
    }

    @Test(expected = PlayersCountException.class)
    public void gameWithoutPlayers() {
        new Game(new ArrayList<>(), spyDisplay, spyBoard, spyReferee);
    }

    @Test
    public void processNextTurn_tiggers() {
        game.processNextTurn();

        assertEquals(
            "doNextMove()",
            firstSpyPlayer.getLastCalledMethod()
        );
    }


    @Test
    @Ignore
    public void showBoardAfterOneTurn() {
        spyDisplay.setNextHumanMove(new Coordinates(0, 0));
        game.processNextTurn();
        game.showUpdatedBoard();

        assertEquals(
            "printBoard(Crossnullnullnullnullnullnullnullnull)",
            spyDisplay.getLastCalledMethod()
        );
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
