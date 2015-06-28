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

        int actual = minimax.calcolateComputerMoveValue(new Coordinates(1, 1), matrix);
        assertEquals(0, actual);
    }

    @Test
    public void computerWinnerWithAMoveAndTwoPossibilities() {
        markMatrixFromString(
            "XOX" +
            "XOX" +
            "O  "
        );

        int computerWinnerMoveValue = minimax.calcolateComputerMoveValue(new Coordinates(1, 2), matrix);
        assertEquals(1, computerWinnerMoveValue);

        int tieMoveValue = minimax.calcolateComputerMoveValue(new Coordinates(2, 2), matrix);
        assertEquals(0, tieMoveValue);
    }

    @Test
    public void computerHasThreePossibilities_andOneBlocksPlayerWin() {
        markMatrixFromString(
            "OXO" +
            " XO" +
            "  X"
        );

        int playerCanStillWin = minimax.calcolateComputerMoveValue(new Coordinates(0, 1), matrix);
        assertEquals(-1, playerCanStillWin);

        playerCanStillWin = minimax.calcolateComputerMoveValue(new Coordinates(0, 2), matrix);
        assertEquals(-1, playerCanStillWin);

        int thisMoveBlockPlayerWin = minimax.calcolateComputerMoveValue(new Coordinates(1, 2), matrix);
        assertEquals(0, thisMoveBlockPlayerWin);
    }

    @Test
    public void ifPlayerCanWinAtTheNextMove_moveValueIsMinusOne() {
        markMatrixFromString(
            "X O" +
            "O  " +
            "OXX"
        );

        int playerCanWinWithTheNextMove = minimax.calcolateComputerMoveValue(new Coordinates(1, 0), matrix);
        assertEquals(-1, playerCanWinWithTheNextMove);

        playerCanWinWithTheNextMove = minimax.calcolateComputerMoveValue(new Coordinates(2, 1), matrix);
        assertEquals(-1, playerCanWinWithTheNextMove);

        int computerWinMove = minimax.calcolateComputerMoveValue(new Coordinates(1, 1), matrix);
        assertEquals(1, computerWinMove);

    }

    @Test
    @Ignore
    public void computerHasFourPossibilities() {
        markMatrixFromString(
            "  X" +
            "XOO" +
            "  X"
        );

        int playerHasTwoPossibilitesToWin = minimax.calcolateComputerMoveValue(new Coordinates(0, 0), matrix);
        assertEquals(-2, playerHasTwoPossibilitesToWin);

        playerHasTwoPossibilitesToWin = minimax.calcolateComputerMoveValue(new Coordinates(0, 2), matrix);
        assertEquals(-2, playerHasTwoPossibilitesToWin);

        int playerAndComputerHasTheSamePossibilities = minimax.calcolateComputerMoveValue(new Coordinates(1, 0), matrix);
        assertEquals(0, playerAndComputerHasTheSamePossibilities);

        playerAndComputerHasTheSamePossibilities = minimax.calcolateComputerMoveValue(new Coordinates(1, 2), matrix);
        assertEquals(0, playerAndComputerHasTheSamePossibilities);
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

    private void playerMark(int x, int y) {
        matrix.playerMark(new Coordinates(x, y));
    }

    private void computerMark(int x, int y) {
        matrix.computerMark(new Coordinates(x, y));
    }

}
