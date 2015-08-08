package TicTacToe;

import TicTacToe.Game.*;
import TicTacToe.Display.ConsoleDisplay;

import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {
        IOBridge io = new IOBridge(System.in, System.out);
        ConsoleDisplay display = new ConsoleDisplay(io);

        Game game = GameFactory.build(display);
        game.play();
    }

}