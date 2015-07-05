package TicTacToe.Display;

import TicTacToe.Cell.Matrix;
import TicTacToe.IOBridge;

public class ConsoleDisplay {
    private final IOBridge io;
    private final MatrixFormatter matrixFormatter;


    public ConsoleDisplay(IOBridge io) {
        this.io = io;
        this.matrixFormatter = new PlainTextMatrixFormatter();
    }

    public void update(Matrix matrix) {
        String formattedString = matrixFormatter.format(matrix);
        io.print(formattedString);
    }

}
