package unit;

import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Player.HumanPlayer;
import TicTacToe.Player.Player;
import helpers.SpyBoard;
import helpers.SpyDisplay;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

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
        assertEquals("Test Human Player", player.getName());
    }

    @Test
    public void doNextMove() {
        instantiatePlayer();
        activateAllSpies();
        spyDisplay.setNextMove(new Coordinates(0, 1));

        player.doNextMove(spyBoard);

        assertEquals(Arrays.asList(
            "askForNextMove name Test Human Player",
            "cellMarkedMessage([0, 1])"
        ), spyDisplay.calls());

        assertEquals(Arrays.asList(
            "mark([0, 1], Cross)"
        ), spyBoard.calls());
    }

    private void activateAllSpies() {
        spyDisplay.activateSpy();
        spyBoard.activateSpy();
    }

    private void instantiatePlayer() {
        player = new HumanPlayer(CellMarkSign.Cross, spyDisplay);
    }
}
