package TicTacToe.Display;

import TicTacToe.Cell.CellStateEnum;
import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;

public class TerminalDisplay implements Display {

    public String show(Matrix matrix) {

        String result = firstHeaderRow();
        for (int y = 0; y < 3; y++)
            result += rowToString(y, matrix);

        return result;
    }

    private String firstHeaderRow() {
        return "  0 1 2\n";
    }

    private String rowToString(int y, Matrix matrix) {
        return y + " " +
            cellStateToChar(matrix.getCellState(new Coordinates(0, y))) + " " +
            cellStateToChar(matrix.getCellState(new Coordinates(1, y))) + " " +
            cellStateToChar(matrix.getCellState(new Coordinates(2, y))) + "\n";
    }

    private char cellStateToChar(CellStateEnum cellState) {
        switch(cellState) {
            case PlayerMarked:
                return 'X';
            case ComputerMarked:
                return 'O';
            default:
                return '-';
        }
    }
}
