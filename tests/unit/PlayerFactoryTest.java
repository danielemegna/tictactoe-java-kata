package unit;

import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Game.GameMode;
import TicTacToe.Player.HumanPlayer;
import TicTacToe.Player.Player;
import TicTacToe.Player.PlayerFactory;
import TicTacToe.Player.UnbeatableComputerPlayer;
import helpers.SpyDisplay;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlayerFactoryTest {

    private PlayerFactory playerFactory;
    private SpyDisplay spyDisplay;

    @Before
    public void setup() {
        spyDisplay = new SpyDisplay();
        playerFactory = new PlayerFactory(spyDisplay);
    }

    @Test
    public void computerVsComputer() {
        assertPlayersFromGameMode(GameMode.ComputerVsComputer, Arrays.asList(
            new UnbeatableComputerPlayer(CellMarkSign.Cross),
            new UnbeatableComputerPlayer(CellMarkSign.Circle)
        ));
    }

    @Test
    public void humanVsComputer() {
        assertPlayersFromGameMode(GameMode.HumanVsComputer, Arrays.asList(
            new HumanPlayer(CellMarkSign.Cross, spyDisplay),
            new UnbeatableComputerPlayer(CellMarkSign.Circle)
        ));
    }

    @Test
    public void humanVsHuman() {
        assertPlayersFromGameMode(GameMode.HumanVsHuman, Arrays.asList(
            new HumanPlayer(CellMarkSign.Cross, spyDisplay),
            new HumanPlayer(CellMarkSign.Circle, spyDisplay)
        ));
    }

    @Test
    public void computerVsHuman() {
        assertPlayersFromGameMode(GameMode.ComputerVsHuman, Arrays.asList(
            new UnbeatableComputerPlayer(CellMarkSign.Cross),
            new HumanPlayer(CellMarkSign.Circle, spyDisplay)
        ));
    }

    private void assertPlayersFromGameMode(GameMode mode, List<Player> expected) {
        List<Player> actual = playerFactory.listFromGameMode(mode);
        assertEquals(expected , actual);
    }
}
