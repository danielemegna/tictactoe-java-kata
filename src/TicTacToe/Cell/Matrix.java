package TicTacToe.Cell;

import TicTacToe.Coordinates.Coordinates;

public class Matrix {

    private Cell[][] matrix;
    private int markedCells;

    public Matrix() {
        this.matrix = new Cell[3][3];
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                this.matrix[x][y] = new Cell();

        this.markedCells = 0;
    }

    public CellStateEnum getCellState(Coordinates c) {
        return getCell(c).getState();
    }

    public void playerMark(Coordinates c) {
        getCell(c).playerMark();
        this.markedCells++;
    }

    public void computerMark(Coordinates c) {
        getCell(c).computerMark();
        this.markedCells++;
    }

    private Cell getCell(Coordinates c) {
        return matrix[c.getX()][c.getY()];
    }

    public boolean isFull() {
        return (this.markedCells == 3 * 3);
    }
}
