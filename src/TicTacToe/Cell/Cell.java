package TicTacToe.Cell;

public class Cell {
    private CellState state;

    public Cell() {
        state = CellState.Empty;
    }

    public CellState getState() {
        return state;
    }

    public boolean isEmpty() {
        return state == CellState.Empty;
    }

    public void playerMark() {
        markWithState(CellState.PlayerMarked);
    }

    public void computerMark() {
        markWithState(CellState.ComputerMarked);
    }

    private void markWithState(CellState newState) {
        if(this.state != CellState.Empty)
            throw new AlreadyMarkedCellAttemptException();

        state = newState;
    }
}
