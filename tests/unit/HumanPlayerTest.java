package unit;

import TicTacToe.Cell.AlreadyMarkedCellAttemptException;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.CoordinateOutOfBoundsException;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Player.HumanPlayer;
import TicTacToe.Player.Player;
import helpers.SpyBoard;
import helpers.SpyDisplay;
import helpers.SpyHelper;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class HumanPlayerTest {

    private SpyDisplay spyDisplay;
    private SpyBoard spyBoard;
    private Player player;

    @Before
    public void setup() {
        spyDisplay = new SpyDisplay();
        spyBoard = new SpyBoard();
        player = new HumanPlayer(CellMarkSign.Cross, spyDisplay);

        SpyHelper.activate(spyDisplay, spyBoard);
    }

    @Test
    public void askForNameToDisplayAtCreation() {
        player = new HumanPlayer(CellMarkSign.Cross, spyDisplay);
        SpyHelper.assertCalls(spyDisplay, "askForHumanPlayerName()");
    }

    @Test
    public void retrievePlayerMarkSign() {
        assertEquals(CellMarkSign.Cross, player.getPlayerCellSign());
    }

    @Test
    public void retrievePlayerName() {
        assertEquals("TestHumanPlayer", player.getName());
    }

    @Test
    public void doNextMove() {
        spyDisplay.setNextMove(new Coordinates(0, 1));

        player.doNextMove(spyBoard);

        SpyHelper.assertCalls(spyDisplay,
            "askForNextMove name TestHumanPlayer",
            "coordinatesMarkedMessage([0, 1])"
        );
        SpyHelper.assertCalls(spyBoard, "mark([0, 1], Cross)");
    }

    @Test
    public void handleCoordinatesOutOfBoundException() {
        spyDisplay.setNextMove(new Coordinates(1, 1));
        spyDisplay.setExceptionAtNextMove(new CoordinateOutOfBoundsException());

        player.doNextMove(spyBoard);

        SpyHelper.assertCalls(spyDisplay,
            "askForNextMove name TestHumanPlayer",
            "invalidCoordinatesMessage()",
            "askForNextMove name TestHumanPlayer",
            "coordinatesMarkedMessage([1, 1])"
        );

        SpyHelper.assertCalls(spyBoard, "mark([1, 1], Cross)");
    }

    @Test
    public void handleAlreadyMarkedCellException() {
        spyDisplay.setNextMove(new Coordinates(2, 0));
        spyDisplay.setExceptionAtNextMove(new AlreadyMarkedCellAttemptException());

        player.doNextMove(spyBoard);

        SpyHelper.assertCalls(spyDisplay,
            "askForNextMove name TestHumanPlayer",
            "alreadyMarkedCellMessage()",
            "askForNextMove name TestHumanPlayer",
            "coordinatesMarkedMessage([2, 0])"
        );

        SpyHelper.assertCalls(spyBoard, "mark([2, 0], Cross)");
    }

    @Test
    public void handleOtherRuntimeExceptions() {
        spyDisplay.setNextMove(new Coordinates(1, 2));
        spyDisplay.setExceptionAtNextMove(new RuntimeException());

        player.doNextMove(spyBoard);

        SpyHelper.assertCalls(spyDisplay,
            "askForNextMove name TestHumanPlayer",
            "invalidInputMessage()",
            "askForNextMove name TestHumanPlayer",
            "coordinatesMarkedMessage([1, 2])"
        );

        SpyHelper.assertCalls(spyBoard,
            "mark([1, 2], Cross)"
        );
    }

    @Test
    public void playersEquality() {
        Player samePlayer           = new HumanPlayer(CellMarkSign.Cross, spyDisplay);
        Player differentPlayer      = new HumanPlayer(CellMarkSign.Circle, spyDisplay);
        BigDecimal differentClass   = BigDecimal.ONE;

        assertEquals(player, samePlayer);
        assertNotEquals(player, differentPlayer);
        assertNotEquals(player, differentClass);
    }

}
