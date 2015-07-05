package component;

import TicTacToe.Cell.Board;
import TicTacToe.ComputerPlayer.Minimax;
import TicTacToe.Coordinates.Coordinates;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinimaxTest {

    private Minimax minimax;
    private Board board;

    @Before
    public void setup() {
        this.minimax = new Minimax();
        this.board = new Board();
    }

    @Test
    public void lastTieMove_worthZero() {
        markBoardFromString(
            "XOO" +
            "O X" +
            "XXO"
        );

        int lastTieMoveValue = evaluateMove(1, 1);
        assertEquals(0, lastTieMoveValue);
    }

    @Test
    public void computerWinWithAMove_worthTwo() {
        markBoardFromString(
            "XOX" +
            "XOX" +
            "O  "
        );

        int computerWinMoveValue = evaluateMove(1, 2);
        assertEquals(2, computerWinMoveValue);

        int tieMoveValue = evaluateMove(2, 2);
        assertEquals(0, tieMoveValue);
    }

    @Test
    public void computerHasThreePossibilities_onlyOneBlocksPlayerWin() {
        markBoardFromString(
            "OXO" +
            " XO" +
            "  X"
        );

        int playerCanStillWin = evaluateMove(0, 1);
        assertEquals(-1, playerCanStillWin);

        playerCanStillWin = evaluateMove(0, 2);
        assertEquals(-1, playerCanStillWin);

        int thisMoveBlocksPlayerWin = evaluateMove(1, 2);
        assertEquals(0, thisMoveBlocksPlayerWin);
    }

    @Test
    public void ifPlayerCanWinAtTheNextMove_moveValueIsMinusOne() {
        markBoardFromString(
            "X O" +
            "O  " +
            "OXX"
        );

        int playerCanWinWithTheNextMove = evaluateMove(1, 0);
        assertEquals(-1, playerCanWinWithTheNextMove);

        playerCanWinWithTheNextMove = evaluateMove(2, 1);
        assertEquals(-1, playerCanWinWithTheNextMove);

        int computerWinMove = evaluateMove(1, 1);
        assertEquals(2, computerWinMove);
    }

    @Test
    public void computerHasFourPossibilitiesThatWorthZero() {
        markBoardFromString(
            "  X" +
            "XOO" +
            "  X"
        );

        assertEquals(0, evaluateMove(0, 0));
        assertEquals(0, evaluateMove(0, 2));
        assertEquals(0, evaluateMove(1, 0));
        assertEquals(0, evaluateMove(1, 2));
    }

    @Test
    public void centerCellIsTheOnlyWayToAvoidDefeat() {
        markBoardFromString(
            "X  " +
            "   " +
            "   "
        );

        int centerCellMove = evaluateMove(1, 1);
        assertEquals(0, centerCellMove);

        assertEquals(-1, evaluateMove(0, 1));
        assertEquals(-1, evaluateMove(0, 2));
        assertEquals(-1, evaluateMove(1, 0));
        assertEquals(-1, evaluateMove(1, 2));
        assertEquals(-1, evaluateMove(2, 0));
        assertEquals(-1, evaluateMove(2, 1));
        assertEquals(-1, evaluateMove(2, 2));
    }

    @Test
    public void someMovesLeadComputerToSureWin() {
        markBoardFromString(
            "  X" +
            "   " +
            "  O"
        );

        // win moves
        assertEquals(1, evaluateMove(0, 0));
        assertEquals(1, evaluateMove(0, 2));
        assertEquals(1, evaluateMove(1, 2));

        // tie moves
        assertEquals(0, evaluateMove(0, 1));
        assertEquals(0, evaluateMove(1, 0));
        assertEquals(0, evaluateMove(1, 1));

        // defeat move
        assertEquals(-1, evaluateMove(2, 1));
    }

    private int evaluateMove(int x, int y) {
        return minimax.calcolateComputerMoveValue(new Coordinates(x, y), board);
    }

    private void playerMark(int x, int y) {
        board.playerMark(new Coordinates(x, y));
    }

    private void computerMark(int x, int y) {
        board.computerMark(new Coordinates(x, y));
    }

    private void markBoardFromString(String s) {
        int index = 0;
        for(int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                switch(s.charAt(index++)) {
                    case 'X':
                        playerMark(x, y);
                        break;
                    case 'O':
                        computerMark(x, y);
                        break;
                }
            }
        }
    }
}
