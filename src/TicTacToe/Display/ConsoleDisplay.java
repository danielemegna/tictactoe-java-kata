package TicTacToe.Display;

import TicTacToe.Cell.Board;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.IOBridge;
import TicTacToe.Game.GameMode;
import TicTacToe.Player.Player;

import java.util.HashMap;

public class ConsoleDisplay {
    private final IOBridge io;
    private final BoardFormatter boardFormatter;

    private final HashMap<String, String> messages = new HashMap<String, String>() {{
        put("welcome", "Welcome!");
        put("shutdown", "Shutting down ... bye bye!");
        put("invalid_input", "Invalid input, retry..");
        put("ask_human_player_name", "Human player name?");
        put("ask_next_move", "%s make your move (x y):");
        put("cell_already_marked", "Cell already marked.. retry.");
        put("invalid_coordinates", "Invalid coordinates.. retry");
        put("coordinates_marked", "Coordinates %s marked.");
        put("winner", "%s is the winner!");
        put("tie", "Tie game, no winner.");
    }};

    public ConsoleDisplay(IOBridge io) {
        this.io = io;
        this.boardFormatter = new BoardFormatter();
    }

    public void welcomeMessage() {
        printFromMessageKey("welcome");
    }

    public void shutDownMessage() {
        printFromMessageKey("shutdown");
    }

    public void announceWinner(Player winner) {
        printFromMessageKey("winner", winner.getName());
    }

    public void announceTie() {
        printFromMessageKey("tie");
    }

    public void invalidInputMessage() {
        printFromMessageKey("invalid_input");
    }

    public void alreadyMarkedCellMessage() {
        printFromMessageKey("already_marked");
    }

    public void invalidCoordinatesMessage() {
        printFromMessageKey("invalid_coordinates");
    }

    public void coordinatesMarkedMessage(Coordinates move) {
        printFromMessageKey("coordinates_marked", move);
    }

    public GameMode askForGameMode() {
        while(true) {
            printGameModeMenu();
            try {
                int mode = Integer.valueOf(io.readLine("->"));
                return GameMode.values()[mode-1];
            } catch (Exception e) {
                invalidInputMessage();
            }
        }
    }

    public String askForHumanPlayerName() {
        String message = getMessageFromKey("ask_human_player_name");
        return io.readLine(message);
    }

    public Coordinates askForNextMove(String playerName) {
        String message = getMessageFromKey("ask_next_move", playerName);
        String input = io.readLine(message);

        int x = Integer.valueOf(String.valueOf(input.charAt(0)));
        int y = Integer.valueOf(String.valueOf(input.charAt(input.length() - 1)));
        return new Coordinates(x, y);
    }

    public void printBoard(Board board) {
        String formattedString = boardFormatter.format(board);
        io.print(formattedString);
    }

    private void printFromMessageKey(String key, Object... args) {
        io.println(getMessageFromKey(key, args));
    }

    private String getMessageFromKey(String key, Object... args) {
        return String.format(messages.get(key), args);
    }

    private void printGameModeMenu() {
        io.println("Select the game mode:");
        io.println("1. Human vs Human");
        io.println("2. Human vs Computer");
        io.println("3. Computer vs Human");
        io.println("4. Computer vs Computer");
    }
}
