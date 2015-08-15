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

    private static final HashMap<String, String> MESSAGES = new HashMap<String, String>() {{
        put("already_marked_cell",      "Cell already marked.. retry.");
        put("ask_human_player_name",    "Human player name?");
        put("ask_next_move",            "%s make your move (x y):");
        put("coordinates_marked",       "Coordinates %s marked.");
        put("invalid_coordinates",      "Invalid coordinates.. retry");
        put("invalid_input",            "Invalid input, retry..");
        put("shutdown",                 "Shutting down ... bye bye!");
        put("tie",                      "Tie game, no winner.");
        put("welcome",                  "Welcome!");
        put("winner",                   "%s is the winner!");
        put("game_mode_menu",           "Select the game mode:\n" +
                                        "1. Human vs Human\n" +
                                        "2. Human vs Computer\n" +
                                        "3. Computer vs Human\n" +
                                        "4. Computer vs Computer\n" +
                                        "->");
    }};

    public ConsoleDisplay(IOBridge ioBridge) {
        this(ioBridge, new BoardFormatter());
    }

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
        printFromMessageKey("already_marked_cell");
    }

    public void invalidCoordinatesMessage() {
        printFromMessageKey("invalid_coordinates");
    }

    public void coordinatesMarkedMessage(Coordinates move) {
        printFromMessageKey("coordinates_marked", move);
    }

    public GameMode askForGameMode() {
        String gameModeMenu = getMessageFromKey("game_mode_menu");
        String input = ioBridge.readNotEmptyLineWithMessage(gameModeMenu);
        int selectedMode = Integer.valueOf(input);
        return GameMode.values()[selectedMode-1];
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
        String formattedBoard = boardFormatter.format(board);
        ioBridge.print(formattedBoard);
    }

    private void printFromMessageKey(String key, Object... args) {
        String message = getMessageFromKey(key, args);
        ioBridge.println(message);
    }

    private String getMessageFromKey(String key, Object... args) {
        String messageToBeFormatted = MESSAGES.get(key);
        return String.format(messageToBeFormatted, args);
    }
}
