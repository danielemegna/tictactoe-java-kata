package TicTacToe;

import TicTacToe.Cell.Board;
import TicTacToe.ComputerPlayer.ComputerPlayer;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Display.Display;
import TicTacToe.Referee.Referee;
import TicTacToe.Referee.Verdict;

public class Game {

    private final String playerName;
    private boolean isPlayerTurn;
    private final ComputerPlayer computerPlayer;
    private final Display display;

    private final Board board;
    private final Referee referee;

    public Game(String playerName, boolean playerStartFirst, ComputerPlayer computerPlayer, Display display) {
        this.playerName = playerName;
        this.computerPlayer = computerPlayer;
        this.display = display;
        this.isPlayerTurn = playerStartFirst;

        this.board = new Board();
        this.referee = new Referee();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void playerMark(int x, int y) {
        if(!isPlayerTurn)
            throw new GameTurnException();

        board.playerMark(new Coordinates(x, y));
        isPlayerTurn = false;
    }

    public void computerMark(int x, int y) {
        if(isPlayerTurn)
            throw new GameTurnException();

        board.computerMark(new Coordinates(x, y));
        isPlayerTurn = true;
    }

    public boolean thereIsAWinner() {
        Verdict v = referee.generateRefereeVerdict(board);
        return v.thereIsAWinner();
    }

    public boolean playerWon() {
        Verdict v = referee.generateRefereeVerdict(board);
        return v.playerIsTheWinner();
    }

    public boolean computerWon() {
        Verdict v = referee.generateRefereeVerdict(board);
        return v.computerIsTheWinner();
    }

    public boolean isBoardFull() {
        return board.isFull();
    }

    public void doTheNextComputerMove() {
        Coordinates c = computerPlayer.establishTheNextMove(board);
        computerMark(c.getX(), c.getY());
    }

    public void updateDisplay() {
        display.update(board);
    }
}
