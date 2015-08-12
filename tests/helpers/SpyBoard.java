package helpers;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SpyBoard extends Board {

    private List<String> calls = new ArrayList<>();
    private boolean registrationEnabled = false;
    private int fullAfter = -1;

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
        if(--fullAfter == 0)
            return true;

        return false;
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

    public void setFullAfter(int fullAfter) {
        this.fullAfter = fullAfter;
    }
}
