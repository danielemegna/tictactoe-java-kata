package TicTacToe.Display;

import TicTacToe.Cell.Board;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.IOBridge;
import TicTacToe.Game.GameMode;
import TicTacToe.Player.Player;

import java.util.HashMap;

public class ConsoleDisplay {
    private final IOBridge ioBridge;
    private final BoardFormatter boardFormatter;

    private final HashMap<String, String> messages = new HashMap<String, String>() {{
        put("ask_human_player_name",    "Human player name?");
        put("ask_next_move",            "%s make your move (x y):");
        put("cell_already_marked",      "Cell already marked.. retry.");
        put("coordinates_marked",       "Coordinates %s marked.");
        put("invalid_coordinates",      "Invalid coordinates.. retry");
        put("invalid_input",            "Invalid input, retry..");
        put("shutdown",                 "Shutting down ... bye bye!");
        put("tie",                      "Tie game, no winner.");
        put("welcome",                  "Welcome!");
        put("winner",                   "%s is the winner!");
    }};

    public ConsoleDisplay(IOBridge ioBridge, BoardFormatter boardFormatter) {
        this.ioBridge = ioBridge;
        this.boardFormatter = boardFormatter;
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
                int selectedMode = Integer.valueOf(ioBridge.readNotEmptyLineWithMessage("->"));
                return GameMode.values()[selectedMode-1];
            } catch (Exception e) {
                invalidInputMessage();
            }
        }
    }

    public String askForHumanPlayerName() {
        String message = getMessageFromKey("ask_human_player_name");
        return ioBridge.readNotEmptyLineWithMessage(message);
    }

    public Coordinates askForNextMove(String playerName) {
        String message = getMessageFromKey("ask_next_move", playerName);
        String input = ioBridge.readNotEmptyLineWithMessage(message);

        int x = Integer.valueOf(String.valueOf(input.charAt(0)));
        int y = Integer.valueOf(String.valueOf(input.charAt(input.length() - 1)));
        return new Coordinates(x, y);
    }

    public void printBoard(Board board) {
        String formattedString = boardFormatter.format(board);
        ioBridge.print(formattedString);
    }

    private void printFromMessageKey(String key, Object... args) {
        ioBridge.println(getMessageFromKey(key, args));
    }

    private String getMessageFromKey(String key, Object... args) {
        return String.format(messages.get(key), args);
    }

    private void printGameModeMenu() {
        ioBridge.println("Select the game mode:");
        ioBridge.println("1. Human vs Human");
        ioBridge.println("2. Human vs Computer");
        ioBridge.println("3. Computer vs Human");
        ioBridge.println("4. Computer vs Computer");
    }
}
