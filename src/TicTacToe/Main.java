package TicTacToe;

import TicTacToe.Cell.AlreadyMarkedCellAttemptException;
import TicTacToe.ComputerPlayer.*;
import TicTacToe.Coordinates.CoordinateOutOfBoundsException;
import TicTacToe.Display.ConsoleDisplay;

import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {
        IOBridge io = new IOBridge(System.in, System.out);

        io.println("Welcome!");
        final String name = io.readLine("What's your name?");

        boolean exit;
        do {
            startANewGame(name, io);
            exit = !io.readYN("Play again?");
        } while(!exit);

        io.println("Shutting down ...");

    }

    private static void startANewGame(String name, IOBridge io) throws IOException, InterruptedException {
        final boolean playerStartFirst = io.readYN("Do you want start first?");

        Game game = new Game(
            name, playerStartFirst,
            new UnbeatableComputerPlayer(),
            new ConsoleDisplay(io)
        );

        io.println("Are you ready " + game.getPlayerName() + "? We're starting!");
        game.updateDisplay();

        boolean skipNextPlayerMove = !playerStartFirst;
        startTheGamePlayLoop(skipNextPlayerMove, game, io);
    }

    private static void startTheGamePlayLoop(boolean skipNextPlayerMove, Game game, IOBridge io) throws InterruptedException {
        while (true) {

            if(!skipNextPlayerMove) {
                try {
                    playerMove(game, io);
                } catch (CoordinateOutOfBoundsException ex) {
                    io.println("Invalid coordinates.. retry");
                    continue;
                } catch (AlreadyMarkedCellAttemptException ex) {
                    io.println("Cell already marked.. retry.");
                    continue;
                } catch (Exception ex) {
                    io.println("Invalid input.. retry");
                    continue;
                }

                game.updateDisplay();

                if (game.playerWon()) {
                    io.println("Congratulations " + game.getPlayerName() + "! You won!");
                    break;
                }
                if (game.isBoardFull()) {
                    io.println("The grid is full.. tie!");
                    break;
                }
            }

            skipNextPlayerMove = false;
            io.println("Computer is thinking...");
            Thread.sleep(500);
            game.doTheNextComputerMove();
            game.updateDisplay();

            if (game.computerWon()) {
                io.println("You lose, computer won!");
                break;
            }
            if (game.isBoardFull()) {
                io.println("The grid is full.. tie!");
                break;
            }

        }
    }

    private static void playerMove(Game game, IOBridge io) throws IOException {
        String input = io.readLine(game.getPlayerName() + " make your move (x y):");
        int x = Integer.valueOf(String.valueOf(input.charAt(0)));
        int y = Integer.valueOf(String.valueOf(input.charAt(input.length() - 1)));
        game.playerMark(x, y);
        io.println("Coordinates [" + x + ", " + y + "] marked.");
    }

}