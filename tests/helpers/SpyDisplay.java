package helpers;

import TicTacToe.Cell.Board;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Player.GameMode;
import TicTacToe.Player.Player;

import java.util.ArrayList;
import java.util.List;

public class SpyDisplay extends ConsoleDisplay {

    private List<String> calls = new ArrayList<>();
    private boolean activatedSpy = false;
    private Coordinates nextMove = null;

    public SpyDisplay() {
        super(null);
    }

    @Override
    public void welcomeMessage() {
        registerNewCall("welcomeMessage()");
    }

    @Override
    public void printBoard(Board board) {
        registerNewCall("printBoard(" + board.getClass() + ")");
    }

    @Override
    public void shutDownMessage() {
        registerNewCall("shutDownMessage()");
    }

    @Override
    public void announceWinner(Player winner) {
        registerNewCall("announceWinner name " + winner.getName());
    }

    @Override
    public void announceTie() {
        registerNewCall("announceTie()");
    }

    @Override
    public void invalidInputMessage() {
        registerNewCall("invalidInputMessage()");
    }

    @Override
    public void alreadyMarkedCellMessage() {
        registerNewCall("alreadyMarkedCellMessage()");
    }

    @Override
    public void invalidCoordinatesMessage() {
        registerNewCall("invalidCoordinatesMessage()");
    }

    @Override
    public GameMode askForGameMode() {
        registerNewCall("askForGameMode()");
        return GameMode.HumanVsComputer;
    }

    @Override
    public String askForHumanPlayerName() {
        registerNewCall("askForHumanPlayerName()");
        return "Test Human Player";
    }

    @Override
    public Coordinates askForNextMove(String playerName) {
        registerNewCall("askForNextMove name " + playerName);
        return nextMove;
    }

    @Override
    public void cellMarkedMessage(Coordinates move) {
        registerNewCall("cellMarkedMessage(" + move + ")");
    }

    public void activateSpy() {
        activatedSpy = true;
    }

    public void deactivateSpy() {
        activatedSpy = false;
    }

    public List<String> calls() {
        return calls;
    }

    private void registerNewCall(String method) {
        if(activatedSpy)
            calls.add(method);
    }

    public void setNextMove(Coordinates nextMove) {
        this.nextMove = nextMove;
    }
}