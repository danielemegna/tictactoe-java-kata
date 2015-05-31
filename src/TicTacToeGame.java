import TicTacToeCell.CellStateEnum;
import Coordinates.TicTacToeCoordinates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TicTacToeGame {

    private String playerName;
    private CellMatrix matrix;
    private final List<Set<TicTacToeCoordinates>> winningCombinations;

    public TicTacToeGame(String playerName) {
        this.playerName = playerName;
        this.matrix = new CellMatrix();
        this.winningCombinations = initWinningCombinations();
    }

    public String getPlayerName() {
        return playerName;
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

        for(Set<TicTacToeCoordinates> wc : winningCombinations) {
            boolean isMatrixCompatible = true;
            for (TicTacToeCoordinates c : wc)
                isMatrixCompatible = isMatrixCompatible & isCellPlayerMarked(c);

            if(isMatrixCompatible)
                return true;
        }

        return false;
    }

    public boolean computerWon() {

        for(Set<TicTacToeCoordinates> winningCombination : winningCombinations) {
            boolean isMatrixCompatible = true;
            for (TicTacToeCoordinates c : winningCombination)
                isMatrixCompatible = isMatrixCompatible & isCellComputerMarked(c);

            if(isMatrixCompatible)
                return true;
        }

        return false;
    }

    private List<Set<TicTacToeCoordinates>> initWinningCombinations() {
        List<Set<TicTacToeCoordinates>> wc = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            final int index = i;
            wc.add(new HashSet<TicTacToeCoordinates>() {{
                add(new TicTacToeCoordinates(0, index));
                add(new TicTacToeCoordinates(1, index));
                add(new TicTacToeCoordinates(2, index));
            }});
            wc.add(new HashSet<TicTacToeCoordinates>() {{
                add(new TicTacToeCoordinates(index, 0));
                add(new TicTacToeCoordinates(index, 1));
                add(new TicTacToeCoordinates(index, 2));
            }});
            wc.add(new HashSet<TicTacToeCoordinates>() {{
                add(new TicTacToeCoordinates(index, index));
                add(new TicTacToeCoordinates(index, index));
                add(new TicTacToeCoordinates(index, index));
            }});
        }

        return wc;
    }

    private boolean isCellPlayerMarked(TicTacToeCoordinates c) {
        return matrix.getCellState(c) == CellStateEnum.PlayerMarked;
    }

    private boolean isCellComputerMarked(TicTacToeCoordinates c) {
        return matrix.getCellState(c) == CellStateEnum.ComputerMarked;
    }
}
