import Coordinates.TicTacToeCoordinates;

public class TicTacToeReferee {

    public TicTacToeReferee() {
    }

    public RefereeVerdict generateRefereeVerdict(CellMatrix matrix) {
        boolean playerIsTheWinner = !matrix.isCellEmpty(new TicTacToeCoordinates(0, 0));
        boolean computerIsTheWinner = false;
        return new RefereeVerdict(playerIsTheWinner, computerIsTheWinner);

    }

    public class RefereeVerdict {

        private final boolean playerIsTheWinner;
        private final boolean computerIsTheWinner;

        public RefereeVerdict(boolean playerIsTheWinner, boolean computerIsTheWinner) {

            this.playerIsTheWinner = playerIsTheWinner;
            this.computerIsTheWinner = computerIsTheWinner;
        }

        public boolean thereIsAWinner() {
            return playerIsTheWinner();
        }

        public boolean playerIsTheWinner() {
            return playerIsTheWinner;
        }

        public boolean computerIsTheWinner() {
            return false;
        }
    }
}
