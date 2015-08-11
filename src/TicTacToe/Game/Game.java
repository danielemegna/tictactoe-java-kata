package TicTacToe.Game;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Player.Player;
import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Referee.Referee;
import TicTacToe.Referee.Verdict;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {

    public static final int NUMBER_OF_PLAYERS = 2;
    private List<Player> players;
    private final ConsoleDisplay display;

    private int playerTurnIndex;
    private final Board board;
    private final Referee referee;

    public Game(List<Player> players, ConsoleDisplay display, Board board, Referee referee) {
        this.display = display;

        this.board = board;
        this.referee = referee;

        checkPlayers(players);
        this.players = players;
        this.playerTurnIndex = 0;
    }

    private void checkPlayers(List<Player> players) {
        if(players.size() < NUMBER_OF_PLAYERS)
            throw new PlayersCountException("Few players passed to Game");
        if(players.size() > NUMBER_OF_PLAYERS)
            throw new PlayersCountException("Too many players passed to Game");

    }

    public void processNextTurn() {
        players
            .get(playerTurnIndex)
            .doNextMove(board);

        increasePlayerTurnIndex();
    }

    public boolean isGameOver() {
        Verdict verdict = referee.generateVedict(board);

        if(!board.isFull() && !verdict.thereIsAWinner())
            return false;

        if(verdict.thereIsAWinner())
            display.announceWinner(getWinnerPlayerFromVerdict(verdict));
        else
            display.announceTie();

        return true;
    }

    public void showUpdatedBoard() {
        display.printBoard(board);
    }

    private void increasePlayerTurnIndex() {
        playerTurnIndex = ((playerTurnIndex + 1) % NUMBER_OF_PLAYERS);
    }

    private Player getWinnerPlayerFromVerdict(Verdict v) {
        for (Player p : players) {
            if (p.getPlayerCellSign() == v.getWinnerSign())
                return p;
        }

        throw new RuntimeException("Cannot find winner player from verdict [" + v + "]");
    }
}
