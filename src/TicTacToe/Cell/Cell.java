package TicTacToe.Cell;

public class Cell {
    private CellStateEnum state;

    public Cell() {
        state = CellStateEnum.Empty;
    }

    public CellStateEnum getState() {
        return state;
    }

    public boolean isEmpty() {
        return state == CellStateEnum.Empty;
    }

    public void playerMark() {
        markWithState(CellStateEnum.PlayerMarked);
    }

    public void computerMark() {
        markWithState(CellStateEnum.ComputerMarked);
    }

    private void markWithState(CellStateEnum newState) {
        if(this.state != CellStateEnum.Empty)
            throw new AlreadyMarkedCellAttemptException();

        state = newState;
    }
}
