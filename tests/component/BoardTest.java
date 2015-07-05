package component;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.AlreadyMarkedCellAttemptException;
import TicTacToe.Cell.CellState;
import TicTacToe.Coordinates.Coordinates;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    @Before
    public void setup() {
        this.board = new Board();
    }

    @Test
    public void inANewCellBoard_CellsAreEmpty() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                assertCellState(new Coordinates(x, y), CellState.Empty);
            }
        }
    }

    @Test
    public void aNewBoardIsNotFull() {
        assertFalse(board.isFull());
    }

    @Test
    public void playerCanMarkEmptyCells() {
        Coordinates c;

        c = new Coordinates(0, 1);
        board.playerMark(c);
        assertCellState(c, CellState.PlayerMarked);
        c = new Coordinates(1, 1);
        board.playerMark(c);
        assertCellState(c, CellState.PlayerMarked);
    }

    @Test
    public void computerCanMarkEmptyCells() {
        Coordinates c;

        c = new Coordinates(0, 1);
        board.computerMark(c);
        assertCellState(c, CellState.ComputerMarked);
        c = new Coordinates(2, 1);
        board.computerMark(c);
        assertCellState(c, CellState.ComputerMarked);
    }

    @Test
    public void marksDoNotAffectOtherCells() {
        board.playerMark(new Coordinates(0, 1));
        board.playerMark(new Coordinates(2, 1));
        board.computerMark(new Coordinates(1, 0));
        board.computerMark(new Coordinates(2, 2));

        assertCellState(new Coordinates(0, 0), CellState.Empty);
        assertCellState(new Coordinates(1, 1), CellState.Empty);
        assertCellState(new Coordinates(1, 2), CellState.Empty);
    }

    private void assertCellState(Coordinates c, CellState expected) {
        CellState actual = board.getCellState(c);
        assertEquals(
                "Fail asserting cell state at coordinates [" + c + "] ." +
                        "Actual [" + actual + "], expected [" + expected + "]",
                expected, actual
        );
    }

    @Test
    public void playersCannotMarkCellTwice() {
        String failMessage = "Expected AlreadyMarkedCellAttemptException has not been thrown!";

        Coordinates playerMarked = new Coordinates(0, 0);
        Coordinates computerMarked = new Coordinates(1, 1);

        board.playerMark(playerMarked);
        board.computerMark(computerMarked);

        try {
            board.playerMark(playerMarked);
            fail(failMessage);
        } catch (AlreadyMarkedCellAttemptException ex) {}
        try {
            board.computerMark(computerMarked);
            fail(failMessage);
        } catch (AlreadyMarkedCellAttemptException ex) {}
    }

    @Test
    public void playersCannotMarkCellsAlreadyMarkedByOthers() {
        String failMessage = "Expected AlreadyMarkedCellAttemptException has not been thrown!";

        Coordinates playerMarked = new Coordinates(0, 0);
        Coordinates computerMarked = new Coordinates(1, 1);

        board.playerMark(playerMarked);
        board.computerMark(computerMarked);

        try {
            board.playerMark(computerMarked);
            fail(failMessage);
        } catch (AlreadyMarkedCellAttemptException ex) {}
        try {
            board.computerMark(playerMarked);
            fail(failMessage);
        } catch (AlreadyMarkedCellAttemptException ex) {}
    }

    @Test
    public void markingEveryCell_boardIsFull() {
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                board.playerMark(new Coordinates(x, y));

        assertTrue(board.isFull());
    }

    @Test
    public void boardCanReturnAllTheEmptyCoordinates() {
        Set<Coordinates> emptyCoordinates;

        emptyCoordinates = board.getEmptyCoordinates();
        assertEquals(9, emptyCoordinates.size());
        assertTrue(
            "Empty coordinates set doesn't contains expected coordinates",
            emptyCoordinates.contains(new Coordinates(1, 1))
        );

        board.playerMark(new Coordinates(1, 1));
        emptyCoordinates = board.getEmptyCoordinates();
        assertEquals(8, emptyCoordinates.size());
        assertFalse(
            "Empty coordinates set contains unexpected coordinates",
            emptyCoordinates.contains(new Coordinates(1, 1))
        );

        board.computerMark(new Coordinates(0, 0));
        emptyCoordinates = board.getEmptyCoordinates();
        assertEquals(7, emptyCoordinates.size());
        assertFalse(
            "Empty coordinates set contains unexpected coordinates",
            emptyCoordinates.contains(new Coordinates(0, 0))
        );

        board.playerMark(new Coordinates(0, 1));
        board.computerMark(new Coordinates(0, 2));
        board.playerMark(new Coordinates(1, 0));
        board.computerMark(new Coordinates(1, 2));
        board.playerMark(new Coordinates(2, 0));
        board.computerMark(new Coordinates(2, 1));

        emptyCoordinates = board.getEmptyCoordinates();
        assertEquals(1, emptyCoordinates.size());
        assertTrue(
            "Empty coordinates set doesn't contains expected coordinates",
            emptyCoordinates.contains(new Coordinates(2, 2))
        );

        board.playerMark(new Coordinates(2, 2));

        emptyCoordinates = board.getEmptyCoordinates();
        assertEquals(0, emptyCoordinates.size());
    }

    @Test
    public void boardIsClonable() {
        Coordinates makedBeforeClone = new Coordinates(0, 0);
        Coordinates markedAfterClone = new Coordinates(0, 1);
        Coordinates markedOnClone = new Coordinates(0, 2);

        board.playerMark(makedBeforeClone);
        Board clone = board.clone();
        board.playerMark(markedAfterClone);
        clone.playerMark(markedOnClone);

        assertEquals(CellState.PlayerMarked,    board.getCellState(makedBeforeClone));
        assertEquals(CellState.PlayerMarked,    clone.getCellState(makedBeforeClone));
        assertEquals(CellState.PlayerMarked,    board.getCellState(markedAfterClone));
        assertEquals(CellState.Empty,           clone.getCellState(markedAfterClone));
        assertEquals(CellState.Empty,           board.getCellState(markedOnClone));
        assertEquals(CellState.PlayerMarked,    clone.getCellState(markedOnClone));
    }

}
