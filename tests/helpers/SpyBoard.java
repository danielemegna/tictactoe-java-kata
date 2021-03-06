package helpers;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SpyBoard extends Board implements Spy {

    private List<String> calls = new ArrayList<>();
    private boolean activatedSpy = false;
    private int fullAfter = -1;
    private Coordinates lastMarkedCoordinates = null;

    @Override
    public Optional<CellMarkSign> getCellSign(Coordinates c) {
        registerNewCall("getCellSign(" + c + ")");
        return Optional.of(CellMarkSign.Cross);
    }

    @Override
    public boolean isCellEmpty(Coordinates c) {
        registerNewCall("isCellEmpty(" + c + ")");
        return super.isCellEmpty(c);
    }

    @Override
    public void mark(Coordinates c, CellMarkSign sign) {
        registerNewCall("mark(" + c + ", " + sign + ")");
        lastMarkedCoordinates = c;
    }

    @Override
    public Coordinates getLastMarkedCoordinates() {
        registerNewCall("getLastMarkedCoordinates()");
        return lastMarkedCoordinates;
    }

    @Override
    public boolean isFull() {
        registerNewCall("isFull()");
        if(fullAfter == 0)
            return true;

        fullAfter--;
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

    public void setFullAfter(int fullAfter) {
        this.fullAfter = fullAfter;
    }

    public void setLastMarkedCoordinates(Coordinates coordinates) {
        lastMarkedCoordinates = coordinates;
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
