package TicTacToe;

import TicTacToe.Cell.AlreadyMarkedCellAttemptException;
import TicTacToe.ComputerPlayer.*;
import TicTacToe.Coordinates.CoordinateOutOfBoundsException;
import TicTacToe.Display.ConsoleDisplay;

import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {
        UserCommunicator uc = new UserCommunicator(System.in, System.out);

        uc.println("Welcome!");
        final String name = uc.readLine("What's your name?");

        boolean exit;
        do {
            startANewGame(name, uc);
            exit = !uc.readYN("Play again?");
        } while(!exit);

        uc.println("Shutting down ...");

    }

    private static void startANewGame(String name, UserCommunicator uc) throws IOException, InterruptedException {
        final boolean playerStartFirst = uc.readYN("Do you want start first?");

        Game game = new Game(
            name, playerStartFirst,
            new UnbeatableComputerPlayer(),
            new ConsoleDisplay(uc)
        );

        uc.println("Are you ready " + game.getPlayerName() + "? We're starting!");
        game.updateDisplay();

        boolean skipNextPlayerMove = !playerStartFirst;
        startTheGamePlayLoop(skipNextPlayerMove, game, uc);
    }

    private static void startTheGamePlayLoop(boolean skipNextPlayerMove, Game game, UserCommunicator uc) throws InterruptedException {
        while (true) {
            if(!skipNextPlayerMove) {
                try {
                    playerMove(game, uc);
                } catch (CoordinateOutOfBoundsException ex) {
                    uc.println("Invalid coordinates.. retry");
                    continue;
                } catch (AlreadyMarkedCellAttemptException ex) {
                    uc.println("Cell already marked.. retry.");
                    continue;
                } catch (Exception ex) {
                    uc.println("Invalid input.. retry");
                    continue;
                }

                game.updateDisplay();

                if (game.playerWon()) {
                    uc.println("Congratulations " + game.getPlayerName() + "! You won!");
                    break;
                }

                if (game.isMatrixFull()) {
                    uc.println("The grid is full.. tie!");
                    break;
                }
            }

            skipNextPlayerMove = false;
            uc.println("Computer is thinking...");
            Thread.sleep(500);
            game.doTheNextComputerMove();
            game.updateDisplay();

            if (game.computerWon()) {
                uc.println("You lose, computer won!");
                break;
            }
            if (game.isMatrixFull()) {
                uc.println("The grid is full.. tie!");
                break;
            }
        }
    }

    private static void playerMove(Game game, UserCommunicator uc) throws IOException {
        String input = uc.readLine(game.getPlayerName() + " make your move (x y):");
        int x = Integer.valueOf(String.valueOf(input.charAt(0)));
        int y = Integer.valueOf(String.valueOf(input.charAt(input.length() - 1)));
        game.playerMark(x, y);
        uc.println("Coordinates [" + x + ", " + y + "] marked.");
    }

}