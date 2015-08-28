package TicTacToe.Cell;

import java.util.Optional;

public class Cell {
    private Optional<CellMarkSign> sign;

    public Cell() {
        sign = Optional.empty();
    }

    public Optional<CellMarkSign> getCurrentSign() {
        return sign;
    }

    public boolean isEmpty() {
        return !getCurrentSign().isPresent();
    }

    public void mark(CellMarkSign newSign) {
        if(sign.isPresent())
            throw new AlreadyMarkedCellAttemptException();
        
        sign = Optional.of(newSign);
    }
}
