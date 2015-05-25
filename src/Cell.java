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

    public void setState(CellStateEnum newState) {
        state = newState;
    }
}
