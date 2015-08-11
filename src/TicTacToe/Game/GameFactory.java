package TicTacToe.Game;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Player.HumanPlayer;
import TicTacToe.Player.Player;
import TicTacToe.Player.UnbeatableComputerPlayer;
import TicTacToe.Referee.Referee;

import java.util.ArrayList;
import java.util.List;

public class GameFactory {

    public static Game build(ConsoleDisplay display) {
        List<Player> players = buildPlayers(display);
        return new Game(players, display, new Board(), new Referee());
    }

    private static List<Player> buildPlayers(ConsoleDisplay display) {
        List<Player> players = new ArrayList<>();
        switch (display.askForGameMode()) {
            case HumanVsHuman:
                players.add(new HumanPlayer(CellMarkSign.Cross, display));
                players.add(new HumanPlayer(CellMarkSign.Circle, display));
                break;
            case HumanVsComputer:
                players.add(new HumanPlayer(CellMarkSign.Cross, display));
                players.add(new UnbeatableComputerPlayer(CellMarkSign.Circle));
                break;
            case ComputerVsHuman:
                players.add(new UnbeatableComputerPlayer(CellMarkSign.Cross));
                players.add(new HumanPlayer(CellMarkSign.Circle, display));
                break;
            case ComputerVsComputer:
                players.add(new UnbeatableComputerPlayer(CellMarkSign.Cross));
                players.add(new UnbeatableComputerPlayer(CellMarkSign.Circle));
                break;
        }
        return players;
    }

}
