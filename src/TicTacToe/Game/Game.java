package TicTacToe.Game;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Player.Player;
import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Player.PlayerFactory;

import java.util.List;

public class Game {

    private Board board;
    private List<Player> players;
    private int playerTurnIndex;

    private final PlayerFactory playerFactory;
    private final ConsoleDisplay display;
    private final Referee referee;

    public Game(ConsoleDisplay display) {
        this(
            new Board(),
            new PlayerFactory(display),
            display,
            new Referee()
        );
    }

    public Game(Board board, PlayerFactory playerFactory, ConsoleDisplay display, Referee refree) {
        this.board = board;
        this.playerFactory = playerFactory;
        this.display = display;
        this.referee = refree;

        display.welcomeMessage();
        initPlayers();
    }

    public void play() {
        showUpdatedBoard();
        gameLoop();
        showGameOutcome();
        display.shutDownMessage();
    }

    private void initPlayers() {
        GameMode mode = display.askForGameMode();
        players = playerFactory.listFromGameMode(mode);
        playerTurnIndex = 0;
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

        display.coordinatesMarkedMessage(board.getLastMarkedCoordinates());
        increasePlayerTurnIndex();
    }

    private void increasePlayerTurnIndex() {
        playerTurnIndex = ((playerTurnIndex + 1) % players.size());
    }

    private boolean isGameOver() {
        return thereIsAWinner() || isTheBoardFull();
    }

    private void showGameOutcome() {
        if(thereIsAWinner()) {
            display.announceWinner(getWinnerPlayer());
            return;
        }

        display.announceTie();
    }

    private boolean thereIsAWinner() {
        return getWinnerPlayer() != null;
    }

    private boolean isTheBoardFull() {
        return board.isFull();
    }

    private Player getWinnerPlayer() {
        CellMarkSign winnerCellMark = referee.getWinnerCellMark(board);
        if(winnerCellMark == null)
            return null;

        return getPlayerFromCellMark(winnerCellMark);
    }

    private Player getPlayerFromCellMark(CellMarkSign sign) {
        Player[] found = players.stream()
            .filter(p -> p.getPlayerCellSign() == sign)
            .toArray(Player[]::new);

        if(found.length == 0)
            throw new Error("Cannot find player for sign [" + sign+ "]");

        return found[0];
    }
}
