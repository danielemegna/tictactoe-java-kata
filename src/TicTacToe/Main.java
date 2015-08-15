package TicTacToe;

import TicTacToe.Game.Game;
import TicTacToe.Display.ConsoleDisplay;

import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {
        IOBridge ioBridge           = new IOBridge(System.in, System.out);
        ConsoleDisplay display      = new ConsoleDisplay(ioBridge);
        Game game                   = new Game(display);

        game.play();
    }


}