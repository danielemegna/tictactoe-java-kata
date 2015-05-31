import TicTacToeCell.CellStateEnum;
import Coordinates.TicTacToeCoordinates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

        List<Set<TicTacToeCoordinates>> winningCombinations = new ArrayList<Set<TicTacToeCoordinates>>() {{
            add(new HashSet<TicTacToeCoordinates>() {{
                add(new TicTacToeCoordinates(0,0));
                add(new TicTacToeCoordinates(1,0));
                add(new TicTacToeCoordinates(2,0));
            }});
            add(new HashSet<TicTacToeCoordinates>() {{
                add(new TicTacToeCoordinates(2,0));
                add(new TicTacToeCoordinates(2,1));
                add(new TicTacToeCoordinates(2,2));
            }});
        }};

        for(Set<TicTacToeCoordinates> winningCombination : winningCombinations) {
            boolean isMatrixCompatible = true;
            for (TicTacToeCoordinates c : winningCombination)
                isMatrixCompatible = isMatrixCompatible & isCellPlayerMarked(c);

            if(isMatrixCompatible)
                return true;
        }

        return false;
    }

    public boolean computerWon() {
        List<Set<TicTacToeCoordinates>> winningCombinations = new ArrayList<Set<TicTacToeCoordinates>>() {{
            add(new HashSet<TicTacToeCoordinates>() {{
                add(new TicTacToeCoordinates(0,0));
                add(new TicTacToeCoordinates(0,1));
                add(new TicTacToeCoordinates(0,2));
            }});
            add(new HashSet<TicTacToeCoordinates>() {{
                add(new TicTacToeCoordinates(0,1));
                add(new TicTacToeCoordinates(1,1));
                add(new TicTacToeCoordinates(2,1));
            }});
        }};

        for(Set<TicTacToeCoordinates> winningCombination : winningCombinations) {
            boolean isMatrixCompatible = true;
            for (TicTacToeCoordinates c : winningCombination)
                isMatrixCompatible = isMatrixCompatible & isCellComputerMarked(c);

            if(isMatrixCompatible)
                return true;
        }

        return false;
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
