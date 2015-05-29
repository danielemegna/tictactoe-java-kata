import TicTacToeCell.Cell;
import TicTacToeCell.CellStateEnum;
import Coordinates.CoordinateOutOfBoundsException;
import Coordinates.TicTacToeCoordinates;

/**
 * Created by daniele on 29/05/15.
 */
public class CellMatrix {

    private Cell[][] cellsMatrix;

    public CellMatrix() {
        this.cellsMatrix = new Cell[3][3];
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                this.cellsMatrix[x][y] = new Cell();
    }


    public Cell getCell(TicTacToeCoordinates c) {
        try {
            return cellsMatrix[c.getX()][c.getY()];
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new CoordinateOutOfBoundsException();
        }
    }

    public void playerMark(TicTacToeCoordinates c) {
        getCell(c).playerMark();
    }

    public void computerMark(TicTacToeCoordinates c) {
        getCell(c).computerMark();
    }

    public boolean isCellEmpty(TicTacToeCoordinates c) {
        return getCell(c).isEmpty();
    }

    public CellStateEnum getCellState(TicTacToeCoordinates c) {
        return getCell(c).getState();
    }
}
