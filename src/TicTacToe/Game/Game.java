package TicTacToe.Game;

import TicTacToe.Cell.Board;
import TicTacToe.Player.GameMode;
import TicTacToe.Player.Player;
import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Player.PlayerFactory;
import TicTacToe.Referee.Referee;
import TicTacToe.Referee.Verdict;

import java.util.List;

public class Game {

    private final ConsoleDisplay display;
    private final PlayerFactory playerFactory;
    private final Referee referee;

    private Board board;
    private List<Player> players;
    private int playerTurnIndex;

    public Game(ConsoleDisplay display, PlayerFactory playerFactory) {
        this(display, playerFactory, new Referee(), new Board());
    }

    public Game(ConsoleDisplay display, PlayerFactory playerFactory, Referee refree, Board board) {
        this.display = display;
        this.playerFactory = playerFactory;
        this.referee = refree;
        this.board = board;

        display.welcomeMessage();
        initPlayers();
    }

    public void initPlayers() {
        GameMode mode = display.askForGameMode();
        players = playerFactory.listFromGameMode(mode);
        playerTurnIndex = 0;
    }

    public void play() {
        showUpdatedBoard();
        gameLoop();
        display.shutDownMessage();
    }

    private void showUpdatedBoard() {
        display.printBoard(board);
    }

    private void gameLoop() {
        do {
            triggerNextPlayer();
            showUpdatedBoard();
        } while(!isGameOver());
    }

    private void triggerNextPlayer() {
        players
            .get(playerTurnIndex)
            .doNextMove(board);

        increasePlayerTurnIndex();
    }

    private void increasePlayerTurnIndex() {
        playerTurnIndex = ((playerTurnIndex + 1) % players.size());
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


    private Player getWinnerPlayerFromVerdict(Verdict v) {
        for (Player p : players) {
            if (p.getPlayerCellSign() == v.getWinnerSign())
                return p;
        }

        throw new RuntimeException("Cannot find winner player from verdict [" + v + "]");
    }
}
