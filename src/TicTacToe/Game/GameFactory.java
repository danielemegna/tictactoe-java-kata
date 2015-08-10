package TicTacToe.Game;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Player.HumanPlayer;
import TicTacToe.Player.UnbeatableComputerPlayer;
import TicTacToe.Referee.Referee;

public class GameFactory {

    public static Game build(ConsoleDisplay display) {
        Game game = new Game(display, new Board(), new Referee());
        initWithMode(display, game);
        return game;
    }

    private static void initWithMode(ConsoleDisplay display, Game game) {
        switch (display.askForGameMode()){
            case HumanVsHuman:
                game.addPlayer(HumanPlayer.build(display, CellMarkSign.Cross));
                game.addPlayer(HumanPlayer.build(display, CellMarkSign.Circle));
                break;
            case HumanVsComputer:
                game.addPlayer(HumanPlayer.build(display, CellMarkSign.Cross));
                game.addPlayer(new UnbeatableComputerPlayer(CellMarkSign.Circle));
                break;
            case ComputerVsHuman:
                game.addPlayer(new UnbeatableComputerPlayer(CellMarkSign.Cross));
                game.addPlayer(HumanPlayer.build(display, CellMarkSign.Circle));
                break;
            case ComputerVsComputer:
                game.addPlayer(new UnbeatableComputerPlayer(CellMarkSign.Cross));
                game.addPlayer(new UnbeatableComputerPlayer(CellMarkSign.Circle));
                break;
        }
    }

}
