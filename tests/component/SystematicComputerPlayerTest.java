package component;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Player.ComputerPlayerChoiceException;
import TicTacToe.Player.SystematicComputerPlayer;
import helpers.BoardTestHelper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SystematicComputerPlayerTest {

    private Board board;
    private SystematicComputerPlayer player;
    private BoardTestHelper helper;

    @Before
    public void setup() {
        board = new Board();
        player = new SystematicComputerPlayer(CellMarkSign.Cross);
        helper = new BoardTestHelper(board);
    }

    @Test
    public void getThePlayerName() {
        assertEquals("SystematicComputerPlayer", player.getName());
    }

    @Test
    public void withEmptyBoard_scpWillChoosesZeroZero() {
        assertNextMove(0, 0);
    }

    @Test
    public void scp_allwaysChoosesFirstEmptyCell() {
        helper.markBoardFromString(
            "XO " +
            " O " +
            "X X"
        );

        assertNextMove(2, 0);
    }

    @Test(expected = ComputerPlayerChoiceException.class)
    public void withAFullBoard_scpThrowsAComputerPlayerChoiceException() {
        helper.markBoardFromString(
            "XOX" +
            "XXO" +
            "OXO"
        );

        player.doNextMove(board);
    }

    private void assertNextMove(int x, int y) {
        helper.assertCellIsEmpty(x, y);
        player.doNextMove(board);
        helper.assertCellSign(x, y, CellMarkSign.Cross);
    }
}
