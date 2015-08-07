package component;

import TicTacToe.Cell.Board;
import TicTacToe.ComputerPlayer.ComputerPlayerChoiceException;
import TicTacToe.ComputerPlayer.SystematicComputerPlayer;
import TicTacToe.Coordinates.Coordinates;
import helpers.BoardTestHelper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SystematicComputerPlayerTest {

    private Board board;
    private SystematicComputerPlayer computerPlayer;
    private BoardTestHelper helper;

    @Before
    public void setup() {
        board = new Board();
        computerPlayer = new SystematicComputerPlayer();
        helper = new BoardTestHelper(board);
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

        computerPlayer.establishTheNextMove(board);
    }

    private void assertNextMove(int x, int y) {
        assertEquals(
            new Coordinates(x, y),
            computerPlayer.establishTheNextMove(board)
        );
    }
}
