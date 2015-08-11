package TicTacToe;

import TicTacToe.Game.*;
import TicTacToe.Display.ConsoleDisplay;

import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {
        IOBridge io = new IOBridge(System.in, System.out);
        ConsoleDisplay display = new ConsoleDisplay(io);
        Game game = GameFactory.build(display);

        display.welcomeMessage();
        game.showUpdatedBoard();
        gameLoop(game);
        display.shutDownMessage();
    }

    private static  void gameLoop(Game game) {
        do {
            game.processNextTurn();
            game.showUpdatedBoard();
        } while(!game.isGameOver());
    }

}