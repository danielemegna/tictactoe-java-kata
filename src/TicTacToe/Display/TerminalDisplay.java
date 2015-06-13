package TicTacToe.Display;

import TicTacToe.Cell.CellStateEnum;
import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;

public class TerminalDisplay implements Display {

    public String show(Matrix matrix) {

        String result = "  0 1 2";
        for (int y = 0; y < 3; y++) {
            String separator = "\n" + y + " ";
            for (int x = 0; x < 3; x++){
                result += separator + cellStateToChar(matrix.getCellState(new Coordinates(x, y)));
                separator = " ";
            }
        }

        return result;
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
