package helpers;

import TicTacToe.Cell.Board;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Player.GameMode;
import TicTacToe.Player.Player;

public class SpyDisplay extends ConsoleDisplay {

    public static final int BOARD_DIMENSION = 3;
    private String lastCalledMethod;
    private Coordinates nextHumanMove;
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
        lastCalledMethod = "printBoard(";
        for(int y = 0; y < BOARD_DIMENSION; y++)
            for(int x = 0; x < BOARD_DIMENSION; x++)
                lastCalledMethod += board.getCellSign(new Coordinates(x, y));

        lastCalledMethod += ")";
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
        return nextHumanMove;
    }

    public String getLastCalledMethod() {
        return lastCalledMethod;
    }

    public void setNextHumanMove(Coordinates nextHumanMove) {
        this.nextHumanMove = nextHumanMove;
    }

    public void setNextGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
}