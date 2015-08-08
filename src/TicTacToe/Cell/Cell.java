package TicTacToe.Cell;

public class Cell {
    private CellMarkSign sign;

    public Cell() {
        sign = null;
    }

    public CellMarkSign getCurrentSign() {
        return sign;
    }

    public boolean isEmpty() {
        return getCurrentSign() == null;
    }

    public void mark(CellMarkSign sign) {
        if(this.sign != null)
            throw new AlreadyMarkedCellAttemptException();

        this.sign = sign;
    }
}
