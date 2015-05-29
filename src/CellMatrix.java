import Cell.Cell;
import Cell.CellStateEnum;
import Coordinates.CoordinateOutOfBoundsException;
import Coordinates.TrisCoordinates;

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


    public Cell getCell(TrisCoordinates c) {
        try {
            return cellsMatrix[c.getX()][c.getY()];
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new CoordinateOutOfBoundsException();
        }
    }

    public void playerMark(TrisCoordinates c) {
        getCell(c).playerMark();
    }

    public void computerMark(TrisCoordinates c) {
        getCell(c).computerMark();
    }

    public boolean isCellEmpty(TrisCoordinates c) {
        return getCell(c).isEmpty();
    }

    public CellStateEnum getCellState(TrisCoordinates c) {
        return getCell(c).getState();
    }
}
