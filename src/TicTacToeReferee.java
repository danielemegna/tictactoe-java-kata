import Coordinates.TicTacToeCoordinates;
import TicTacToeCell.CellStateEnum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TicTacToeReferee {

    private final List<Set<TicTacToeCoordinates>> winningCombinations;

    public TicTacToeReferee() {
        this.winningCombinations = initWinningCombinations();
    }

    public RefereeVerdict generateRefereeVerdict(CellMatrix matrix) {
        boolean playerIsTheWinner = false;
        boolean computerIsTheWinner = false;

        for(Set<TicTacToeCoordinates> wc : winningCombinations) {
            playerIsTheWinner = computerIsTheWinner = true;

            for (TicTacToeCoordinates c : wc) {
                CellStateEnum cellState = matrix.getCellState(c);
                playerIsTheWinner &= (cellState == CellStateEnum.PlayerMarked);
                computerIsTheWinner &= (cellState == CellStateEnum.ComputerMarked);
            }

            if(playerIsTheWinner || computerIsTheWinner)
                break;
        }

        return new RefereeVerdict(playerIsTheWinner, computerIsTheWinner);
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

    public class RefereeVerdict {

        private final boolean playerIsTheWinner;
        private final boolean computerIsTheWinner;

        public RefereeVerdict(boolean playerIsTheWinner, boolean computerIsTheWinner) {
            this.playerIsTheWinner = playerIsTheWinner;
            this.computerIsTheWinner = computerIsTheWinner;
        }

        public boolean thereIsAWinner() {
            return playerIsTheWinner() || computerIsTheWinner();
        }

        public boolean playerIsTheWinner() {
            return playerIsTheWinner;
        }

        public boolean computerIsTheWinner() {
            return computerIsTheWinner;
        }
    }
}
