package unit;

import TicTacToe.Cell.AlreadyMarkedCellAttemptException;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.CoordinateOutOfBoundsException;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Player.HumanPlayer;
import TicTacToe.Player.Player;
import helpers.SpyBoard;
import helpers.SpyDisplay;
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
    }

    @Test
    public void askForNameToDisplayAtCreation() {
        activateAllSpies();
        instantiatePlayer();

        assertEquals(Arrays.asList(
            "askForHumanPlayerName()"
        ), spyDisplay.calls());
    }

    @Test
    public void retrievePlayerMarkSign() {
        instantiatePlayer();
        assertEquals(CellMarkSign.Cross, player.getPlayerCellSign());
    }

    @Test
    public void retrievePlayerName() {
        instantiatePlayer();
        assertEquals("TestHumanPlayer", player.getName());
    }

    @Test
    public void doNextMove() {
        instantiatePlayer();
        activateAllSpies();
        spyDisplay.setNextMove(new Coordinates(0, 1));

        player.doNextMove(spyBoard);

        assertEquals(Arrays.asList(
            "askForNextMove name TestHumanPlayer",
            "cellMarkedMessage([0, 1])"
        ), spyDisplay.calls());

        assertEquals(Arrays.asList(
            "mark([0, 1], Cross)"
        ), spyBoard.calls());
    }

    @Test
    public void handleCoordinatesOutOfBoundException() {
        instantiatePlayer();
        activateAllSpies();
        spyDisplay.setNextMove(new Coordinates(1, 1));
        spyDisplay.setExceptionAtNextMove(new CoordinateOutOfBoundsException());

        player.doNextMove(spyBoard);

        assertEquals(Arrays.asList(
            "askForNextMove name TestHumanPlayer",
            "invalidCoordinatesMessage()",
            "askForNextMove name TestHumanPlayer",
            "cellMarkedMessage([1, 1])"
        ), spyDisplay.calls());

        assertEquals(Arrays.asList(
            "mark([1, 1], Cross)"
        ), spyBoard.calls());
    }

    @Test
    public void handleAlreadyMarkedCellException() {
        instantiatePlayer();
        activateAllSpies();
        spyDisplay.setNextMove(new Coordinates(2, 0));
        spyDisplay.setExceptionAtNextMove(new AlreadyMarkedCellAttemptException());

        player.doNextMove(spyBoard);

        assertEquals(Arrays.asList(
            "askForNextMove name TestHumanPlayer",
            "alreadyMarkedCellMessage()",
            "askForNextMove name TestHumanPlayer",
            "cellMarkedMessage([2, 0])"
        ), spyDisplay.calls());

        assertEquals(Arrays.asList(
            "mark([2, 0], Cross)"
        ), spyBoard.calls());
    }

    @Test
    public void handleOtherRuntimeExceptions() {
        instantiatePlayer();
        activateAllSpies();
        spyDisplay.setNextMove(new Coordinates(1, 2));
        spyDisplay.setExceptionAtNextMove(new RuntimeException());

        player.doNextMove(spyBoard);

        assertEquals(Arrays.asList(
            "askForNextMove name TestHumanPlayer",
            "invalidInputMessage()",
            "askForNextMove name TestHumanPlayer",
            "cellMarkedMessage([1, 2])"
        ), spyDisplay.calls());

        assertEquals(Arrays.asList(
            "mark([1, 2], Cross)"
        ), spyBoard.calls());
    }

    @Test
    public void playersEquality() {
        instantiatePlayer();
        Player samePlayer           = new HumanPlayer(CellMarkSign.Cross, spyDisplay);
        Player differentPlayer      = new HumanPlayer(CellMarkSign.Circle, spyDisplay);
        BigDecimal differentClass   = BigDecimal.ONE;

        assertEquals(player, samePlayer);
        assertNotEquals(player, differentPlayer);
        assertNotEquals(player, differentClass);
    }

    private void instantiatePlayer() {
        player = new HumanPlayer(CellMarkSign.Cross, spyDisplay);
    }

    private void activateAllSpies() {
        spyDisplay.activateSpy();
        spyBoard.activateSpy();
    }
}
