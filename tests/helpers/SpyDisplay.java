package helpers;

import TicTacToe.Cell.Board;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Player.GameMode;
import TicTacToe.Player.Player;

public class SpyDisplay extends ConsoleDisplay {

    private String lastCalledMethod;
    private Coordinates nextMove;
    private GameMode gameMode;


    public SpyDisplay() {
        super(null);
    }

    @Override
    public void welcomeMessage() {
        lastCalledMethod = "welcomeMessage()";
    }

    @Override
    public void printBoard(Board board) {
        lastCalledMethod = "printBoard(board)";
    }

    @Override
    public void shutDownMessage() {
        lastCalledMethod = "shutDownMessage()";
    }

    @Override
    public void announceWinner(Player winner) {
        lastCalledMethod = "announceWinner name " + winner.getName();
    }

    @Override
    public void announceTie() {
        lastCalledMethod = "announceTie()";
    }

    @Override
    public void invalidInputMessage() {
        lastCalledMethod = "invalidInputMessage()";
    }

    @Override
    public void alreadyMarkedCellMessage() {
        lastCalledMethod = "alreadyMarkedCellMessage()";
    }

    @Override
    public void invalidCoordinatesMessage() {
        lastCalledMethod = "invalidCoordinatesMessage()";
    }

    @Override
    public GameMode askForGameMode() {
        lastCalledMethod = "askForGameMode()";
        return gameMode;
    }

    @Override
    public String askForHumanPlayerName() {
        lastCalledMethod = "askForHumanPlayerName()";
        return "Test Human Player";
    }

    @Override
    public Coordinates askForNextMove(String playerName) {
        lastCalledMethod = "askForNextMove name " + playerName;
        return nextMove;
    }

    public String getLastCalledMethod() {
        return lastCalledMethod;
    }

    public void setNextMove(Coordinates nextMoveWhenAsked) {
        this.nextMove = nextMoveWhenAsked;
    }

    public void setNextGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
}