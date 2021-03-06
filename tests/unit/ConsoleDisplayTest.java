package unit;

import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Game.GameMode;
import helpers.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsoleDisplayTest {

    private ConsoleDisplay display;
    private SpyIOBridge spyIOBridge;
    private SpyBoardFormatter spyBoardFormatter;

    @Before
    public void setup() {
        spyIOBridge = new SpyIOBridge();
        spyBoardFormatter = new SpyBoardFormatter();
        display = new ConsoleDisplay(spyIOBridge, spyBoardFormatter);

        SpyHelper.activate(spyIOBridge, spyBoardFormatter);
    }

    @Test
    public void welcomeMessage() {
        display.welcomeMessage();
        SpyHelper.assertCalls(spyIOBridge, "println(Welcome!)");
    }

    @Test
    public void shutDownMessage() {
        display.shutDownMessage();
        SpyHelper.assertCalls(spyIOBridge, "println(Shutting down ... bye bye!)");
    }

    @Test
    public void announcingWinner() {
        display.announceWinner(new SpyPlayer(CellMarkSign.Cross, "BobTheSpy"));
        SpyHelper.assertCalls(spyIOBridge, "println(BobTheSpy is the winner!)");
    }

    @Test
    public void announcingTie() {
        display.announceTie();
        SpyHelper.assertCalls(spyIOBridge, "println(Tie game, no winner.)");
    }

    @Test
    public void askForHumanPlayerName() {
        display.askForHumanPlayerName();
        SpyHelper.assertCalls(spyIOBridge, "readNotEmptyLineWithMessage(Human player name?)");
    }

    @Test
    public void askForGameMode_HumanVsHuman() {
        spyIOBridge.setNextReadLineOutput("3");
        GameMode mode = display.askForGameMode();

        assertEquals(GameMode.ComputerVsHuman, mode);
        SpyHelper.assertCalls(spyIOBridge,
            "readNotEmptyLineWithMessage(Select the game mode:\n" +
            "1. Human vs Human\n" +
            "2. Human vs Computer\n" +
            "3. Computer vs Human\n" +
            "4. Computer vs Computer\n" +
            "->)"
        );
    }

    @Test
    public void askForNextMove() {
        spyIOBridge.setNextReadLineOutput("2, 0");

        Coordinates move = display.askForNextMove("TestPlayerName");

        SpyHelper.assertCalls(spyIOBridge, "readNotEmptyLineWithMessage(TestPlayerName make your move (x y):)");
        assertEquals(new Coordinates(2, 0), move);
    }

    @Test
    public void printBoard() {
        display.printBoard(new SpyBoard());

        SpyHelper.assertCalls(spyIOBridge, "print(formatted_board)");
        SpyHelper.assertCalls(spyBoardFormatter, "format(class helpers.SpyBoard)");
    }

}
