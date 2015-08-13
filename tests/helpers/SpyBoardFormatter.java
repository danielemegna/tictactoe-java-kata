package helpers;

import TicTacToe.Cell.Board;
import TicTacToe.Display.BoardFormatter;

import java.util.ArrayList;
import java.util.List;

public class SpyBoardFormatter extends BoardFormatter {

    private List<String> calls = new ArrayList<>();
    private boolean activatedSpy = false;

    @Override
    public String format(Board board) {
        registerNewCall("format(" + board.getClass() + ")");
        return "formatted_board";
    }

    public void activateSpy() {
        activatedSpy = true;
    }

    public void deactivateSpy() {
        activatedSpy = false;
    }

    public List<String> calls() {
        return calls;
    }

    private void registerNewCall(String method) {
        if(activatedSpy)
            calls.add(method);
    }
}
