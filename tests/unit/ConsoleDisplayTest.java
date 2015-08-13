package unit;

import TicTacToe.Display.ConsoleDisplay;
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
}
