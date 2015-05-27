/**
 * Created by daniele on 22/05/15.
 */
public class Cell {
    private CellStateEnum state;

    public Cell() {
        state = CellStateEnum.Empty;
    }

    public CellStateEnum getState() {
        return state;
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
