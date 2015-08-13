package unit;

import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Game.GameMode;
import TicTacToe.Player.HumanPlayer;
import TicTacToe.Player.Player;
import TicTacToe.Player.PlayerFactory;
import TicTacToe.Player.UnbeatableComputerPlayer;
import helpers.SpyDisplay;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlayerFactoryTest {

    @Test
    public void computerVsComputer() {
        SpyDisplay spyDisplay = new SpyDisplay();
        PlayerFactory playerFactory = new PlayerFactory(spyDisplay);

        List<Player> players = playerFactory.listFromGameMode(GameMode.ComputerVsComputer);

        assertEquals(Arrays.asList(
            new UnbeatableComputerPlayer(CellMarkSign.Cross),
            new UnbeatableComputerPlayer(CellMarkSign.Circle)
        ), players);
    }

    @Test
    public void humanVsComputer() {
        SpyDisplay spyDisplay = new SpyDisplay();
        PlayerFactory playerFactory = new PlayerFactory(spyDisplay);

        List<Player> players = playerFactory.listFromGameMode(GameMode.HumanVsComputer);

        assertEquals(Arrays.asList(
            new HumanPlayer(CellMarkSign.Cross, spyDisplay),
            new UnbeatableComputerPlayer(CellMarkSign.Circle)
        ), players);
    }
}
