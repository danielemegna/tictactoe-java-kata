package TicTacToe.Display;

import TicTacToe.Cell.CellStateEnum;
import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;

public class TerminalDisplay implements Display {

    public String show(Matrix matrix) {
        return "- - -\n- - -\n- - -";
    }
}
