package TicTacToe.Display;

import TicTacToe.Cell.Matrix;
import TicTacToe.UserCommunicator;

public class ConsoleDisplay {
    private final UserCommunicator userCommunicator;
    private final MatrixFormatter matrixFormatter;


    public ConsoleDisplay(UserCommunicator uc) {
        this.userCommunicator = uc;
        this.matrixFormatter = new PlainTextMatrixFormatter();
    }

    public void update(Matrix matrix) {
        String formattedString = matrixFormatter.format(matrix);
        userCommunicator.print(formattedString);
    }

}
