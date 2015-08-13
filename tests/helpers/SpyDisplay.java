package helpers;

import TicTacToe.Cell.Board;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Game.GameMode;
import TicTacToe.Player.Player;

import java.util.ArrayList;
import java.util.List;

public class SpyDisplay extends ConsoleDisplay {

    private List<String> calls = new ArrayList<>();
    private boolean activatedSpy = false;
    private Coordinates nextMove = null;
    private RuntimeException exceptionAtNextMove;

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
        return "TestHumanPlayer";
    }

    @Override
    public Coordinates askForNextMove(String playerName) {
        registerNewCall("askForNextMove name " + playerName);

        if(exceptionAtNextMove != null) {
            RuntimeException ex = exceptionAtNextMove;
            exceptionAtNextMove = null;
            throw ex;
        }

        return nextMove;
    }

    @Override
    public void coordinatesMarkedMessage(Coordinates move) {
        registerNewCall("coordinatesMarkedMessage(" + move + ")");
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


    public void setExceptionAtNextMove(RuntimeException exceptionAtNextMove) {
        this.exceptionAtNextMove = exceptionAtNextMove;
    }
}