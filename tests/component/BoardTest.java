package component;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.AlreadyMarkedCellAttemptException;
import TicTacToe.Cell.CellState;
import TicTacToe.Coordinates.Coordinates;
import helpers.BoardTestHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class BoardTest {

    private Board board;
    private BoardTestHelper helper;

    @Before
    public void setup() {
        board = new Board();
        helper = new BoardTestHelper(board);
    }

    @Test
    public void inANewBoard_CellsAreEmpty() {
        helper.assertCellState(0, 0, CellState.Empty);
        helper.assertCellState(1, 2, CellState.Empty);
    }

    @Test
    public void aNewBoardIsNotFull() {
        assertFalse(board.isFull());
    }

    @Test
    public void playerCanMarkEmptyCells() {
        helper.playerMark(0, 1);
        helper.assertCellState(0, 1, CellState.PlayerMarked);
    }

    @Test
    public void computerCanMarkEmptyCells() {
        helper.computerMark(0, 1);
        helper.assertCellState(0, 1, CellState.ComputerMarked);
    }

    @Test
    public void marksDoNotAffectOtherCells() {
        helper.playerMark(0, 1);
        helper.computerMark(2, 2);

        helper.assertCellState(0, 0, CellState.Empty);
        helper.assertCellState(1, 2, CellState.Empty);
    }

    @Test(expected = AlreadyMarkedCellAttemptException.class)
    public void playerCannotMarkCellTwice() {
        helper.markBoardFromString(
            "X  " +
            " O " +
            "   "
        );

        helper.playerMark(0, 0);
    }

    @Test(expected = AlreadyMarkedCellAttemptException.class)
    public void playerCannotMarkCellsAlreadyMarkedByComputer() {
        helper.markBoardFromString(
            "X  " +
            " O " +
            "   "
        );

        helper.playerMark(1, 1);
    }

    @Test
    public void markingEveryCell_boardIsFull() {
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                helper.playerMark(x, y);

        assertTrue(board.isFull());
    }

    @Test
    public void emptyBoardReturnsAll9CoordinatesAsEmpty() {
        Set<Coordinates> emptyCoordinates = board.getEmptyCoordinates();

        assertEquals(9, emptyCoordinates.size());
        assertTrue(
            "Empty coordinates set doesn't contains expected coordinates",
            emptyCoordinates.contains(new Coordinates(1, 1))
        );
    }

    @Test
    public void boardDoesntReturnsMarkedCellAsEmptyCoordinates() {
        helper.playerMark(1, 1);
        Set<Coordinates> emptyCoordinates = board.getEmptyCoordinates();

        assertEquals(8, emptyCoordinates.size());
        assertFalse(
            "Empty coordinates set contains unexpected coordinates",
            emptyCoordinates.contains(new Coordinates(1, 1))
        );
    }

    @Test
    public void boardWithOnlyOneEmptyCoordinates_returnsThatAsEmpty() {
        helper.markBoardFromString(
            "OXX" +
            "XXO" +
            "OO "
        );

        Set<Coordinates> emptyCoordinates = board.getEmptyCoordinates();
        assertEquals(1, emptyCoordinates.size());
        assertTrue(
            "Empty coordinates set doesn't contains expected coordinates",
            emptyCoordinates.contains(new Coordinates(2, 2))
        );
    }

    @Test
    public void fullBoardHasNoEmptyCoordinates() {
        helper.markBoardFromString(
            "OXX" +
            "XXO" +
            "OOX"
        );

        Set<Coordinates> emptyCoordinates = board.getEmptyCoordinates();
        assertEquals(0, emptyCoordinates.size());
    }

    @Test
    public void cloningABoard_CellsRemainsMarkedOnTheClone() {
        helper.markBoardFromString(
            "X  " +
            "   " +
            " O "
        );

        Board clone = board.clone();
        BoardTestHelper cloneHelper = new BoardTestHelper(clone);

        cloneHelper.assertCellState(0, 0, CellState.PlayerMarked);
        cloneHelper.assertCellState(1, 2, CellState.ComputerMarked);
    }

    @Test
    public void cloningABoard_EmptyCellsRemainsEmpty() {
        helper.markBoardFromString(
            "XOX" +
            "   " +
            "X  "
        );

        Board clone = board.clone();
        BoardTestHelper cloneHelper = new BoardTestHelper(clone);

        cloneHelper.assertCellState(0, 1, CellState.Empty);
        cloneHelper.assertCellState(2, 2, CellState.Empty);
    }

    @Test
    public void marksOnClonedBoardDontAffectOriginOnes() {
        helper.markBoardFromString(
            "OOX" +
            "   " +
            "  O"
        );

        Board clone = board.clone();
        BoardTestHelper cloneHelper = new BoardTestHelper(clone);

        cloneHelper.playerMark(1, 1);
        helper.assertCellState(1, 1, CellState.Empty);
    }
}
