package unit;

import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Game.GameMode;
import helpers.SpyIOBridge;
import helpers.SpyPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ConsoleDisplayTest {

    private SpyIOBridge spyIOBridge;
    private ConsoleDisplay display;

    @Before
    public void setup() {
        spyIOBridge = new SpyIOBridge();
        display = new ConsoleDisplay(spyIOBridge);
        spyIOBridge.activateSpy();
    }

    @Test
    public void welcomeMessage() {
        display.welcomeMessage();

        assertEquals(Arrays.asList(
            "println(Welcome!)"
        ), spyIOBridge.calls());
    }

    @Test
    public void shutDownMessage() {
        display.shutDownMessage();

        assertEquals(Arrays.asList(
            "println(Shutting down ... bye bye!)"
        ), spyIOBridge.calls());
    }

    @Test
    public void askForGameMode_HumanVsHuman() {
        spyIOBridge.setReadLineOutput("3");
        GameMode mode = display.askForGameMode();

        assertEquals(GameMode.ComputerVsHuman, mode);
        assertEquals(Arrays.asList(
            "println(Select the game mode:)",
            "println(1. Human vs Human)",
            "println(2. Human vs Computer)",
            "println(3. Computer vs Human)",
            "println(4. Computer vs Computer)",
            "readLine(->)"
        ), spyIOBridge.calls());
    }

    @Test
    public void askForHumanPlayerName() {
        display.askForHumanPlayerName();

        assertEquals(Arrays.asList(
            "readLine(Human player name?)"
        ), spyIOBridge.calls());
    }

    @Test
    public void announcingWinner() {
        display.announceWinner(new SpyPlayer(CellMarkSign.Cross, "BobTheSpy"));
        assertEquals(Arrays.asList(
            "println(BobTheSpy is the winner!)"
        ), spyIOBridge.calls());
    }

    @Test
    public void announcingTie() {
        display.announceTie();
        assertEquals(Arrays.asList(
            "println(Tie game, no winner.)"
        ), spyIOBridge.calls());
    }
}
