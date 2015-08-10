package TicTacToe.Game;

import TicTacToe.Cell.Board;
import TicTacToe.Player.Player;
import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Referee.Referee;
import TicTacToe.Referee.Verdict;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Player> players;
    private final ConsoleDisplay display;

    private int playerTurnBit;
    private final Board board;
    private final Referee referee;

    Game(ConsoleDisplay display, Board board, Referee referee) {
        this.display = display;

        this.board = board;
        this.referee = referee;

        this.players = new ArrayList<>();
        this.playerTurnBit = 0;
    }

    public void play() {
        display.welcomeMessage();
        gameLoop();
        display.shutDownMessage();
    }

    private void gameLoop() {
        do {
            showUpdatedBoard();
            processNextTurn();
        } while(!isGameOver());
    }

    private void showUpdatedBoard() {
        display.printBoard(board);
    }

    private void processNextTurn() {
        players
            .get(playerTurnBit)
            .doNextMove(board);

        increaseTurnBit();
    }

    private boolean isGameOver() {
        Verdict verdict = referee.generateVedict(board);

        if(!board.isFull() && !verdict.thereIsAWinner())
            return false;

        if(verdict.thereIsAWinner())
            display.announceWinner(getWinnerPlayerFromVerdict(verdict));
        else
            display.announceTie();

        return true;
    }

    private void increaseTurnBit() {
        playerTurnBit = ((playerTurnBit + 1) % 2);
    }

    private Player getWinnerPlayerFromVerdict(Verdict v) {
        for(Player p : players) {
            if (p.getPlayerCellSign() == v.getWinnerSign())
                return p;
        }

        throw new RuntimeException("Cannot find winner player from verdict [" + v + "]");
    }

    void addPlayer(Player player) {
        players.add(player);
    }
}
