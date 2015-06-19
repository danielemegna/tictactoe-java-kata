package TicTacToe.Display;

import TicTacToe.Cell.Matrix;
import java.io.PrintStream;

public class ConsoleDisplay {
    private final PrintStream printStream;
    private final MatrixFormatter matrixFormatter;


    public ConsoleDisplay(PrintStream ps) {
        this.printStream = ps;
        this.matrixFormatter = new PlainTextMatrixFormatter();
    }

    public void update(Matrix matrix) {
        String formattedString = matrixFormatter.format(matrix);
        printStream.print(formattedString);
    }

}
