package helpers;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Referee.Referee;

import java.util.ArrayList;
import java.util.List;

public class SpyReferee extends Referee {

    private List<String> calls = new ArrayList<>();
    private boolean registrationEnabled = false;
    private CellMarkSign winner = null;

    @Override
    public CellMarkSign getWinnerCellMark(Board board) {
        registerNewCall("getWinnerCellMark(" + board.getClass() + ")");
        return winner;
    }

    public void startRegistration() {
        registrationEnabled = true;
    }

    public void stopRegistration() {
        registrationEnabled = false;
    }

    public List<String> calls() {
        return calls;
    }

    private void registerNewCall(String method) {
        if(registrationEnabled)
            calls.add(method);
    }

    public void setWinner(CellMarkSign markSign) {
        winner = markSign;
    }
}
