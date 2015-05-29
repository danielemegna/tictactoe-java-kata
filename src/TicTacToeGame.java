import TicTacToeCell.CellStateEnum;
import Coordinates.TicTacToeCoordinates;

import java.util.HashSet;
import java.util.Set;

public class TicTacToeGame {

    private String playerName;
    private CellMatrix matrix;

    public TicTacToeGame(String playerName) {
        this.playerName = playerName;
        this.matrix = new CellMatrix();
    }

    public void playerMark(int x, int y) {
        matrix.playerMark(new TicTacToeCoordinates(x, y));
    }

    public void computerMark(int x, int y) {
        matrix.computerMark(new TicTacToeCoordinates(x, y));
    }

    public boolean thereIsAWinner() {
        return playerWon() || computerWon();
    }

    public boolean playerWon() {

        Set<TicTacToeCoordinates> winningCombination = new HashSet<TicTacToeCoordinates>() {{
            add(new TicTacToeCoordinates(0,0));
            add(new TicTacToeCoordinates(1,0));
            add(new TicTacToeCoordinates(2,0));
        }};

        boolean win = true;
        for(TicTacToeCoordinates c : winningCombination)
            win = win & isCellPlayerMarked(c);

        return win || (
            isCellPlayerMarked(new TicTacToeCoordinates(2,0)) &&
            isCellPlayerMarked(new TicTacToeCoordinates(2,1)) &&
            isCellPlayerMarked(new TicTacToeCoordinates(2,2))
        );
    }

    public boolean computerWon() {
        return
        (
            isCellComputerMarked(new TicTacToeCoordinates(0,0)) &&
            isCellComputerMarked(new TicTacToeCoordinates(0,1)) &&
            isCellComputerMarked(new TicTacToeCoordinates(0,2))
        ) || (
            isCellComputerMarked(new TicTacToeCoordinates(0,1)) &&
            isCellComputerMarked(new TicTacToeCoordinates(1,1)) &&
            isCellComputerMarked(new TicTacToeCoordinates(2,1))
        );
    }

    public String getPlayerName() {
        return playerName;
    }

    private boolean isCellPlayerMarked(TicTacToeCoordinates c) {
        return matrix.getCellState(c) == CellStateEnum.PlayerMarked;
    }

    private boolean isCellComputerMarked(TicTacToeCoordinates c) {
        return matrix.getCellState(c) == CellStateEnum.ComputerMarked;
    }
}
