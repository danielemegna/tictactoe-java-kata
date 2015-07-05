package TicTacToe.Display;

import TicTacToe.Cell.Board;
import TicTacToe.IOBridge;

public class ConsoleDisplay {
    private final IOBridge io;
    private final BoardFormatter boardFormatter;


    public ConsoleDisplay(IOBridge io) {
        this.io = io;
        this.boardFormatter = new PlainTextBoardFormatter();
    }

    public void update(Board board) {
        String formattedString = boardFormatter.format(board);
        io.print(formattedString);
    }

}
