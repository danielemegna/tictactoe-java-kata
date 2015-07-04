package component;

import TicTacToe.Cell.Matrix;
import TicTacToe.ComputerPlayer.Minimax;
import TicTacToe.Coordinates.Coordinates;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinimaxTest {

    private Minimax minimax;
    private Matrix matrix;

    @Before
    public void setup() {
        this.minimax = new Minimax();
        this.matrix = new Matrix();
    }

    @Test
    public void lastTieMove_worthZero() {
        markMatrixFromString(
            "XOO" +
            "O X" +
            "XXO"
        );

        int lastTieMoveValue = evaluateMove(1, 1);
        assertEquals(0, lastTieMoveValue);
    }

    @Test
    public void computerWinWithAMove_worthTwo() {
        markMatrixFromString(
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
        markMatrixFromString(
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
        markMatrixFromString(
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
        markMatrixFromString(
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
        markMatrixFromString(
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
        markMatrixFromString(
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
        return minimax.calcolateComputerMoveValue(new Coordinates(x, y), matrix);
    }

    private void playerMark(int x, int y) {
        matrix.playerMark(new Coordinates(x, y));
    }

    private void computerMark(int x, int y) {
        matrix.computerMark(new Coordinates(x, y));
    }

    private void markMatrixFromString(String s) {
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
