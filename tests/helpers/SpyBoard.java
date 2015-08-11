package helpers;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SpyBoard extends Board {

    private List<String> calls = new ArrayList<>();

    @Override
    public CellMarkSign getCellSign(Coordinates c) {
        registerNewCall("getCellSign(" + c + ")");
        return CellMarkSign.Cross;
    }

    @Override
    public void mark(Coordinates c, CellMarkSign sign) {
        registerNewCall("mark(" + c + ", " + sign + ")");
    }

    @Override
    public boolean isFull() {
        registerNewCall("isFull()");
        return true;
    }

    @Override
    public Set<Coordinates> getEmptyCoordinates() {
        registerNewCall("getEmptyCoordinates()");
        return null;
    }

    @Override
    public Board clone() {
        registerNewCall("clone()");
        return this;
    }

    public List<String> calls() {
        return calls;
    }

    private void registerNewCall(String method) {
        calls.add(method);
    }

    public String getLastCall() {
        return calls.get(calls.size()-1);
    }
}
