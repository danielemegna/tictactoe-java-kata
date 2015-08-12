package TicTacToe;

import TicTacToe.Cell.Board;
import TicTacToe.Game.Game;
import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Player.PlayerFactory;
import TicTacToe.Referee.Referee;

import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {
        IOBridge io                 = new IOBridge(System.in, System.out);
        ConsoleDisplay display      = new ConsoleDisplay(io);
        PlayerFactory playerFactory = new PlayerFactory(display);
        Game game                   = new Game(display, playerFactory, new Referee(), new Board());

        game.play();
    }


}