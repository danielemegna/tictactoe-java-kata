import TicTacToeCell.Cell;
import TicTacToeCell.CellStateEnum;
import Coordinates.TicTacToeCoordinates;

public class CellMatrix {

    private Cell[][] matrix;

    public CellMatrix() {
        this.matrix = new Cell[3][3];
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                this.matrix[x][y] = new Cell();
    }

    public CellStateEnum getCellState(TicTacToeCoordinates c) {
        return getCell(c).getState();
    }

    public void playerMark(TicTacToeCoordinates c) {
        getCell(c).playerMark();
    }

    public void computerMark(TicTacToeCoordinates c) {
        getCell(c).computerMark();
    }

    private Cell getCell(TicTacToeCoordinates c) {
        return matrix[c.getX()][c.getY()];
    }
}
