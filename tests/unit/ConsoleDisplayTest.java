package unit;

import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Game.GameMode;
import helpers.SpyIOBridge;
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
}
