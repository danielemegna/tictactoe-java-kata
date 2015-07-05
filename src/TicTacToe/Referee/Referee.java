package TicTacToe.Referee;

import TicTacToe.Cell.Board;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Cell.CellState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Referee {

    private final List<Set<Coordinates>> winningCombinations = new ArrayList<>();

    public Referee() {
        initWinningCombinations();
    }

    public Verdict generateRefereeVerdict(Board board) {
        boolean playerIsTheWinner = false;
        boolean computerIsTheWinner = false;

        for(Set<Coordinates> wc : winningCombinations) {
            playerIsTheWinner = computerIsTheWinner = true;

            for (Coordinates c : wc) {
                CellState cellState = board.getCellState(c);
                playerIsTheWinner &= (cellState == CellState.PlayerMarked);
                computerIsTheWinner &= (cellState == CellState.ComputerMarked);
            }

            if(playerIsTheWinner || computerIsTheWinner)
                break;
        }

        return new Verdict(playerIsTheWinner, computerIsTheWinner);
    }

    private void initWinningCombinations() {
        addLinearWinningCombinations();
        addDiagonalWinningCombinations();
    }

    private void addLinearWinningCombinations() {
        for(int i = 0; i < 3; i++) {
            final int index = i;
            winningCombinations.add(new HashSet<Coordinates>() {{
                add(new Coordinates(0, index));
                add(new Coordinates(1, index));
                add(new Coordinates(2, index));
            }});
            winningCombinations.add(new HashSet<Coordinates>() {{
                add(new Coordinates(index, 0));
                add(new Coordinates(index, 1));
                add(new Coordinates(index, 2));
            }});
        }
    }

    private void addDiagonalWinningCombinations() {
        winningCombinations.add(new HashSet<Coordinates>() {{
            add(new Coordinates(0, 0));
            add(new Coordinates(1, 1));
            add(new Coordinates(2, 2));
        }});

        winningCombinations.add(new HashSet<Coordinates>() {{
            add(new Coordinates(2, 0));
            add(new Coordinates(1, 1));
            add(new Coordinates(0, 2));
        }});
    }
}
