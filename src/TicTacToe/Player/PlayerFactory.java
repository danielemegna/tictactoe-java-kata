package TicTacToe.Player;

import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Game.GameMode;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {

    private final ConsoleDisplay display;

    public PlayerFactory(ConsoleDisplay display) {
        this.display  = display;
    }

    public List<Player> listFromGameMode(GameMode mode) {
        List<Player> players = new ArrayList<>();
        switch (mode) {
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
