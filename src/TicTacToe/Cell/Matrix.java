package TicTacToe.Cell;

import TicTacToe.Coordinates.Coordinates;

public class Matrix {

    private Cell[][] matrix;

    public Matrix() {
        this.matrix = new Cell[3][3];
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                this.matrix[x][y] = new Cell();
    }

    public CellStateEnum getCellState(Coordinates c) {
        return getCell(c).getState();
    }

    public void playerMark(Coordinates c) {
        getCell(c).playerMark();
    }

    public void computerMark(Coordinates c) {
        getCell(c).computerMark();
    }

    private Cell getCell(Coordinates c) {
        return matrix[c.getX()][c.getY()];
    }

    public boolean isFull() {
        for(int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (getCellState(new Coordinates(x, y)) == CellStateEnum.Empty)
                    return false;
            }
        }

        return true;
    }
}
