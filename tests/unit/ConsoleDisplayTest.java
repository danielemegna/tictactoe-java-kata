package unit;

import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Game.GameMode;
import helpers.SpyBoard;
import helpers.SpyBoardFormatter;
import helpers.SpyIOBridge;
import helpers.SpyPlayer;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ConsoleDisplayTest {

    private SpyIOBridge spyIOBridge;
    private ConsoleDisplay display;
    private SpyBoardFormatter spyBoardFormatter;

    @Before
    public void setup() {
        spyIOBridge = new SpyIOBridge();
        spyBoardFormatter = new SpyBoardFormatter();
        display = new ConsoleDisplay(spyIOBridge, spyBoardFormatter);

        spyIOBridge.activateSpy();
        spyBoardFormatter.activateSpy();
    }

    @Test
    public void welcomeMessage() {
        display.welcomeMessage();
        assertSpyIOBridgeCalls("println(Welcome!)");
    }

    @Test
    public void shutDownMessage() {
        display.shutDownMessage();
        assertSpyIOBridgeCalls("println(Shutting down ... bye bye!)");
    }

    @Test
    public void announcingWinner() {
        display.announceWinner(new SpyPlayer(CellMarkSign.Cross, "BobTheSpy"));
        assertSpyIOBridgeCalls("println(BobTheSpy is the winner!)");
    }

    @Test
    public void announcingTie() {
        display.announceTie();
        assertSpyIOBridgeCalls("println(Tie game, no winner.)");
    }

    @Test
    public void askForHumanPlayerName() {
        display.askForHumanPlayerName();
        assertSpyIOBridgeCalls("readLine(Human player name?)");
    }

    @Test
    public void askForGameMode_HumanVsHuman() {
        spyIOBridge.setReadLineOutput("3");
        GameMode mode = display.askForGameMode();

        assertEquals(GameMode.ComputerVsHuman, mode);
        assertSpyIOBridgeCalls(
            "println(Select the game mode:)",
            "println(1. Human vs Human)",
            "println(2. Human vs Computer)",
            "println(3. Computer vs Human)",
            "println(4. Computer vs Computer)",
            "readLine(->)"
        );
    }

    @Test
    public void printBoard() {
        display.printBoard(new SpyBoard());

        assertSpyIOBridgeCalls("print(formatted_board)");
        assertEquals(Arrays.asList(
            "format(class helpers.SpyBoard)"
        ), spyBoardFormatter.calls());
    }

    private void assertSpyIOBridgeCalls(String ... expectedCalls) {
        assertEquals(Arrays.asList(expectedCalls), spyIOBridge.calls());
    }
}
