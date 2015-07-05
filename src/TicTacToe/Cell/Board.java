package TicTacToe.Cell;

import TicTacToe.Coordinates.Coordinates;

import java.util.HashSet;
import java.util.Set;

public class Board {

    private static final int BOARD_DIMENSION = 3;

    private Cell[][] cells;
    private int markedCells;

    public Board() {
        this.cells = new Cell[BOARD_DIMENSION][BOARD_DIMENSION];
        for(int x = 0; x < BOARD_DIMENSION; x++)
            for(int y = 0; y < BOARD_DIMENSION; y++)
                this.cells[x][y] = new Cell();

        this.markedCells = 0;
    }

    public CellState getCellState(Coordinates c) {
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

    public boolean isFull() {
        return (this.markedCells == BOARD_DIMENSION * BOARD_DIMENSION);
    }

    public Set<Coordinates> getEmptyCoordinates() {
        Set<Coordinates> emptyCoordinates = new HashSet<>();
        for(int x = 0; x < BOARD_DIMENSION; x++) {
            for (int y = 0; y < BOARD_DIMENSION; y++) {
                Coordinates c = new Coordinates(x, y);
                if (getCellState(c) == CellState.Empty)
                    emptyCoordinates.add(new Coordinates(x, y));
            }
        }

        return emptyCoordinates;
    }

    public Board clone() {
        Board clone = new Board();
        for(int x = 0; x < BOARD_DIMENSION; x++) {
            for (int y = 0; y < BOARD_DIMENSION; y++) {
                Coordinates c = new Coordinates(x, y);
                if(this.getCellState(c) == CellState.PlayerMarked) {
                    clone.playerMark(c);
                    continue;
                }
                if(this.getCellState(c) == CellState.ComputerMarked) {
                    clone.computerMark(c);
                    continue;
                }
            }
        }

        return clone;
    }

    private Cell getCell(Coordinates c) {
        return cells[c.getX()][c.getY()];
    }
}
