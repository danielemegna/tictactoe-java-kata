package component;

import TicTacToe.Cell.Matrix;
import TicTacToe.ComputerPlayer.Minimax;
import TicTacToe.Coordinates.Coordinates;
import org.junit.Before;
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

        int actual = minimax.calcolateMoveValue(new Coordinates(1, 1), matrix);
        assertEquals(0, actual);
    }

    @Test
    public void computerWinnerWithAMoveAndTwoPossibilities() {
        markMatrixFromString(
            "XOX" +
            "XOX" +
            "O  "
        );

        int computerWinnerMoveValue = minimax.calcolateMoveValue(new Coordinates(1, 2), matrix);
        assertEquals(1, computerWinnerMoveValue);

        int tieMoveValue = minimax.calcolateMoveValue(new Coordinates(2, 2), matrix);
        assertEquals(0, tieMoveValue);
    }

    @Test
    public void computerHasThreePossibilities_andOneBlockPlayerWin() {
        markMatrixFromString(
            "OXO" +
            " XO" +
            "  X"
        );

        int playerCanStillWin = minimax.calcolateMoveValue(new Coordinates(0, 1), matrix);
        assertEquals(-1, playerCanStillWin);

        int anotherPlayerCanStillWin = minimax.calcolateMoveValue(new Coordinates(0, 2), matrix);
        assertEquals(-1, anotherPlayerCanStillWin);

        int thisMoveBlockPlayerWin = minimax.calcolateMoveValue(new Coordinates(1, 2), matrix);
        assertEquals(0, thisMoveBlockPlayerWin);
    }

    @Test
    public void computerHasFourPossibilities() {
        markMatrixFromString(
            "  X" +
            "XOO" +
            "  X"
        );

        int playerHasTwoPossibilitesToWin = minimax.calcolateMoveValue(new Coordinates(0, 0), matrix);
        assertEquals(-2, playerHasTwoPossibilitesToWin);

        playerHasTwoPossibilitesToWin = minimax.calcolateMoveValue(new Coordinates(0, 2), matrix);
        assertEquals(-2, playerHasTwoPossibilitesToWin);

        int playerAndComputerHasTheSamePossibilities = minimax.calcolateMoveValue(new Coordinates(1, 0), matrix);
        assertEquals(0, playerAndComputerHasTheSamePossibilities);

        playerAndComputerHasTheSamePossibilities = minimax.calcolateMoveValue(new Coordinates(1, 2), matrix);
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
