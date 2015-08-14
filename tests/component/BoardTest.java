package component;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.AlreadyMarkedCellAttemptException;
import TicTacToe.Cell.CellMarkSign;
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
        helper.assertCellIsEmpty(0, 0);
        helper.assertCellIsEmpty(1, 2);
    }

    @Test
    public void aNewBoardIsNotFull() {
        assertFalse(board.isFull());
    }

    @Test
    public void markEmptyCells() {
        helper.mark(0, 1, CellMarkSign.Cross);
        helper.mark(1, 2, CellMarkSign.Circle);

        helper.assertCellSign(0, 1, CellMarkSign.Cross);
        helper.assertCellSign(1, 2, CellMarkSign.Circle);
    }

    @Test
    public void marksDoNotAffectOtherCells() {
        helper.mark(1, 0, CellMarkSign.Cross);
        helper.mark(2, 2, CellMarkSign.Circle);

        helper.assertCellIsEmpty(0, 1);
        helper.assertCellIsEmpty(1, 2);
    }

    @Test(expected = AlreadyMarkedCellAttemptException.class)
    public void markACellTwice() {
        helper.markBoardFromString(
            "X  " +
            " O " +
            "   "
        );

        helper.mark(0, 0, CellMarkSign.Cross);
    }

    @Test
    public void markingEveryCell_boardIsFull() {
        helper.markBoardFromString(
            "XOX" +
            "XOX" +
            "OXO"
        );

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
        helper.mark(1, 1, CellMarkSign.Cross);
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

        cloneHelper.assertCellSign(0, 0, CellMarkSign.Cross);
        cloneHelper.assertCellSign(1, 2, CellMarkSign.Circle);
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

        cloneHelper.assertCellIsEmpty(0, 1);
        cloneHelper.assertCellIsEmpty(2, 2);
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

        cloneHelper.mark(1, 1, CellMarkSign.Cross);
        helper.assertCellIsEmpty(1, 1);
    }

    @Test
    public void retrieveLastMarkedCoordinates() {
        helper.mark(1, 0, CellMarkSign.Cross);
        helper.mark(2, 0, CellMarkSign.Circle);

        assertEquals(new Coordinates(2, 0), board.getLastMarkedCoordinates());
    }
}
