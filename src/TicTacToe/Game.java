package TicTacToe;

import TicTacToe.Cell.Matrix;
import TicTacToe.ComputerPlayer.ComputerPlayer;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Referee.Referee;
import TicTacToe.Referee.Verdict;

public class Game {

    private String playerName;
    private ComputerPlayer computerPlayer;

    private Matrix matrix;
    private Referee referee;
    private boolean isPlayerTurn;

    public Game(String playerName, ComputerPlayer computerPlayer) {
        this.playerName = playerName;
        this.computerPlayer = computerPlayer;

        this.matrix = new Matrix();
        this.referee = new Referee();
        this.isPlayerTurn = true;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void playerMark(int x, int y) {
        if(!isPlayerTurn)
            throw new GameTurnException();

        matrix.playerMark(new Coordinates(x, y));
        isPlayerTurn = false;
    }

    public void computerMark(int x, int y) {
        if(isPlayerTurn)
            throw new GameTurnException();

        matrix.computerMark(new Coordinates(x, y));
        isPlayerTurn = true;
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

    public boolean isMatrixFull() {
        return matrix.isFull();
    }

    public void doTheNextComputerMove() {
        Coordinates c = computerPlayer.establishTheNextMove(matrix);
        computerMark(c.getX(), c.getY());
    }
}
