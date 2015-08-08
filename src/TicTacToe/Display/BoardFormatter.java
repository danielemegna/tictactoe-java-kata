package TicTacToe.Display;

import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Cell.Board;
import TicTacToe.Coordinates.Coordinates;

public class BoardFormatter {

    public String format(Board board) {

        String result = firstHeaderRow();
        for (int y = 0; y < 3; y++)
            result += rowToString(y, board);

        return result;
    }

    private String firstHeaderRow() {
        return "  0 1 2\n";
    }

    private String rowToString(int y, Board board) {
        return y + " " +
            cellToChar(0, y, board) + " " +
            cellToChar(1, y, board) + " " +
            cellToChar(2, y, board) + "\n";
    }

    private char cellToChar(int x, int y, Board board) {
        CellMarkSign sign = board.getCellSign(new Coordinates(x, y));
        return cellSignToChar(sign);
    }

    private char cellSignToChar(CellMarkSign cellSign) {
        if(cellSign == null)
            return '-';

        switch(cellSign) {
            case Cross:
                return 'X';
            case Circle:
                return 'O';
            default:
                return '-';
        }
    }
}
