package TicTacToe.Cell;

import TicTacToe.Coordinates.Coordinates;

import java.util.HashSet;
import java.util.Set;

public class Board {

    private static final int BOARD_DIMENSION = 3;

    private Cell[][] cells;
    private int markedCells;
    protected Coordinates lastMarkedCoordinates;

    public Board() {
        cells = new Cell[BOARD_DIMENSION][BOARD_DIMENSION];

        for(int x = 0; x < BOARD_DIMENSION; x++)
            for(int y = 0; y < BOARD_DIMENSION; y++)
                cells[x][y] = new Cell();

        markedCells = 0;
        lastMarkedCoordinates = null;
    }

    public CellMarkSign getCellSign(Coordinates c) {
        return getCell(c).getCurrentSign();
    }

    public boolean isCellEmpty(Coordinates c) {
        return getCellSign(c) == null;
    }

    public void mark(Coordinates c, CellMarkSign sign) {
        getCell(c).mark(sign);
        lastMarkedCoordinates = c;
        markedCells++;
    }

    public Coordinates getLastMarkedCoordinates() {
        return lastMarkedCoordinates;
    }

    public boolean isFull() {
        return (markedCells == BOARD_DIMENSION * BOARD_DIMENSION);
    }

    public Set<Coordinates> getEmptyCoordinates() {
        Set<Coordinates> emptyCoordinates = new HashSet<>();

        for(int x = 0; x < BOARD_DIMENSION; x++) {
            for (int y = 0; y < BOARD_DIMENSION; y++) {
                Coordinates c = new Coordinates(x, y);
                if (getCell(c).isEmpty())
                    emptyCoordinates.add(c);
            }
        }

        return emptyCoordinates;
    }

    public Board clone() {
        Board clone = new Board();
        for(int x = 0; x < BOARD_DIMENSION; x++) {
            for (int y = 0; y < BOARD_DIMENSION; y++) {
                Coordinates c = new Coordinates(x, y);
                if(!getCell(c).isEmpty())
                    clone.mark(c, getCellSign(c));
            }
        }
        return clone;
    }

    private Cell getCell(Coordinates c) {
        return cells[c.getX()][c.getY()];
    }
}
