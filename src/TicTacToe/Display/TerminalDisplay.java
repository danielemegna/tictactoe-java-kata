package TicTacToe.Display;

import TicTacToe.Cell.CellStateEnum;
import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;

public class TerminalDisplay implements Display {

    public String show(Matrix matrix) {
        if(matrix.getCellState(new Coordinates(1, 0)) == CellStateEnum.PlayerMarked)
            return "- X -\n- - -\n- - -";

        return "- - -\n- - -\n- - -";
    }
}
