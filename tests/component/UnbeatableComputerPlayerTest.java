package component;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Player.ComputerPlayerChoiceException;
import TicTacToe.Player.UnbeatableComputerPlayer;
import helpers.BoardTestHelper;
import org.junit.Before;
import org.junit.Test;

public class UnbeatableComputerPlayerTest {

    private UnbeatableComputerPlayer player;
    private Board board;
    private BoardTestHelper helper;

    @Before
    public void setup() {
        player = new UnbeatableComputerPlayer(CellMarkSign.Circle);
        board = new Board();
        helper = new BoardTestHelper(board);
    }

    @Test
    public void onePossibleMove() {
        helper.markBoardFromString(
            "XOO" +
            "O X" +
            "XXO"
        );
        assertNextMove(1, 1);
    }

    @Test
    public void winnerInAMove() {
        helper.markBoardFromString(
            "XOX" +
            "XOX" +
            "O  "
        );
        assertNextMove(1, 2);
    }

    @Test
    public void blockAdversaryWin() {
        helper.markBoardFromString(
            "OXO" +
            " XO" +
            "  X"
        );
        assertNextMove(1, 2);
    }

    @Test
    public void centerCellIsTheBestSecondMove() {
        helper.markBoardFromString(
            "X  " +
            "   " +
            "   "
        );
        assertNextMove(1, 1);
    }

    @Test(expected = ComputerPlayerChoiceException.class)
    public void fullBoard() {
        helper.markBoardFromString(
            "XOO" +
            "OOX" +
            "XXO"
        );
        player.doNextMove(board);
    }

    private void assertNextMove(int x, int y) {
        helper.assertCellIsEmpty(x, y);
        player.doNextMove(board);
        helper.assertCellSign(x, y, CellMarkSign.Circle);
    }

}
