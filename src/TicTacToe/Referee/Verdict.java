package TicTacToe.Referee;

public class Verdict {

    private final boolean playerIsTheWinner;
        private final boolean computerIsTheWinner;

        public Verdict(boolean playerIsTheWinner, boolean computerIsTheWinner) {
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
