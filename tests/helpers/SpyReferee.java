package helpers;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Game.Referee;

import java.util.ArrayList;
import java.util.List;

public class SpyReferee extends Referee implements Spy {

    private List<String> calls = new ArrayList<>();
    private boolean activatedSpy = false;
    private CellMarkSign winner = null;
    private int winnerAfter = -1;

    @Override
    public CellMarkSign getWinnerCellMark(Board board) {
        registerNewCall("getWinnerCellMark(" + board.getClass() + ")");
        if(winnerAfter == 0)
            return winner;

        winnerAfter--;
        return null;
    }

    public void setWinner(CellMarkSign markSign) {
        winner = markSign;
    }

    public void setWinnerAfter(int winnerAfter) {
        this.winnerAfter = winnerAfter;
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
