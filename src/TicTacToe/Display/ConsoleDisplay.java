package TicTacToe.Display;

import TicTacToe.Cell.Board;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.IOBridge;
import TicTacToe.Game.GameMode;
import TicTacToe.Player.Player;

public class ConsoleDisplay {
    private final IOBridge io;
    private final BoardFormatter boardFormatter;

    public ConsoleDisplay(IOBridge io) {
        this.io = io;
        this.boardFormatter = new BoardFormatter();
    }

    public void welcomeMessage() {
        io.println("Welcome!");
    }

    public void shutDownMessage() {
        io.println("Shutting down ... bye bye!");
    }

    public GameMode askForGameMode() {
        while(true) {
            io.println("Select the game mode:");
            io.println("1. Human vs Human");
            io.println("2. Human vs Computer");
            io.println("3. Computer vs Human");
            io.println("4. Computer vs Computer");
            try {
                int mode = Integer.valueOf(io.readLine("->"));
                return GameMode.values()[mode-1];
            } catch (Exception e) {
                io.println("Invalid input, retry..");
            }
        }
    }

    public String askForHumanPlayerName() {
        return io.readLine("Human player name?");
    }

    public void printBoard(Board board) {
        String formattedString = boardFormatter.format(board);
        io.print(formattedString);
    }

    public void announceWinner(Player winner) {
        io.println(winner.getName() + " is the winner!");
    }

    public void announceTie() {
        io.println("Tie game, no winner.");
    }

    public Coordinates askForNextMove(String playerName) {
        String input = io.readLine(playerName + " make your move (x y):");
        int x = Integer.valueOf(String.valueOf(input.charAt(0)));
        int y = Integer.valueOf(String.valueOf(input.charAt(input.length() - 1)));
        return new Coordinates(x, y);
    }

    public void invalidInputMessage() {
        io.println("Invalid input.. retry");
    }

    public void alreadyMarkedCellMessage() {
        io.println("Cell already marked.. retry.");
    }

    public void invalidCoordinatesMessage() {
        io.println("Invalid coordinates.. retry");
    }

    public void cellMarkedMessage(Coordinates move) {
        io.println("Coordinates " + move.toString() + " marked.");
    }
}
