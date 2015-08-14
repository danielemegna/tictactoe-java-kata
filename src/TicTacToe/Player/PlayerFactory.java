package TicTacToe.Player;

import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Game.GameMode;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {

    private final ConsoleDisplay display;
    private List<Player> players;

    public PlayerFactory(ConsoleDisplay display) {
        this.display  = display;
    }

    public List<Player> listFromGameMode(GameMode mode) {
        players = new ArrayList<>();
        switch (mode) {
            case HumanVsHuman:
                addHumanPlayer();
                addHumanPlayer();
                break;
            case HumanVsComputer:
                addHumanPlayer();
                addComputerPlayer();
                break;
            case ComputerVsHuman:
                addComputerPlayer();
                addHumanPlayer();
                break;
            case ComputerVsComputer:
                addComputerPlayer();
                addComputerPlayer();
                break;
        }
        return players;
    }

    private void addHumanPlayer() {
        Player humanPlayer = new HumanPlayer(getNextPlayerSign(), display);
        players.add(humanPlayer);
    }

    private void addComputerPlayer() {
        Player computerPlayer = new UnbeatableComputerPlayer(getNextPlayerSign());
        players.add(computerPlayer);
    }

    private CellMarkSign getNextPlayerSign() {
        int nextPlayerSignsIndex = players.size();
        return CellMarkSign.values()[nextPlayerSignsIndex];
    }
}
