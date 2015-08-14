package TicTacToe.Game;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Player.Player;
import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Player.PlayerFactory;

import java.util.List;

public class Game {

    private final ConsoleDisplay display;
    private final PlayerFactory playerFactory;
    private final Referee referee;

    private Board board;
    private List<Player> players;
    private int playerTurnIndex;

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

        display.coordinatesMarkedMessage(board.getLastMarkedCoordinates());
        increasePlayerTurnIndex();
    }

    private void increasePlayerTurnIndex() {
        playerTurnIndex = ((playerTurnIndex + 1) % players.size());
    }

    private boolean isGameOver() {
        CellMarkSign winnerCellMark = referee.getWinnerCellMark(board);

        if(!board.isFull() && winnerCellMark == null)
            return false;

        if(winnerCellMark != null)
            display.announceWinner(getPlayerFromCellMark(winnerCellMark));
        else
            display.announceTie();

        return true;
    }


    private Player getPlayerFromCellMark(CellMarkSign sign) {
        for (Player p : players) {
            if (p.getPlayerCellSign() == sign)
                return p;
        }

        throw new RuntimeException("Cannot find player for sign [" + sign+ "]");
    }
}
