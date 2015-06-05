package TicTacToe;

import TicTacToe.Cell.Matrix;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Referee.Referee;
import TicTacToe.Referee.Verdict;

public class Game {

    private String playerName;
    private Matrix matrix;
    private Referee referee;

    public Game(String playerName) {
        this.playerName = playerName;
        this.matrix = new Matrix();
        this.referee = new Referee();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void playerMark(int x, int y) {
        matrix.playerMark(new Coordinates(x, y));
    }

    public void computerMark(int x, int y) {
        matrix.computerMark(new Coordinates(x, y));
    }

    public boolean thereIsAWinner() {
        Verdict v = referee.generateRefereeVerdict(matrix);
        return v.thereIsAWinner();
    }

    public boolean playerWon() {
        Verdict v = referee.generateRefereeVerdict(matrix);
        return v.playerIsTheWinner();
    }

    public boolean computerWon() {
        Verdict v = referee.generateRefereeVerdict(matrix);
        return v.computerIsTheWinner();
    }

    public boolean isFinished() {
        return matrix.isFull();
    }
}
