import TicTacToe.Cell.AlreadyMarkedCellAttemptException;
import TicTacToe.ComputerPlayer.*;
import TicTacToe.Coordinates.CoordinateOutOfBoundsException;
import TicTacToe.*;
import TicTacToe.Display.ConsoleDisplay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {
        UserCommunicator uc = new UserCommunicator(
            new BufferedReader(new InputStreamReader(System.in)),
            System.out
        );

        uc.println("Welcome!");
        final String name = uc.printMessageAndReadLine("What's your name?");

        boolean exit;
        do {
            final boolean playerStartFirst = uc.readYN("Do you want to start first?");

            Game game = new Game(
                name, playerStartFirst,
                new UnbeatableComputerPlayer(),
                new ConsoleDisplay(System.out)
            );

            uc.println("Are you ready " + game.getPlayerName() + "? We're starting!");
            game.updateDisplay();

            boolean skipNextPlayerMove = !playerStartFirst;
            while (true) {
                if(!skipNextPlayerMove) {

                    try {
                        String input = uc.printMessageAndReadLine(game.getPlayerName() + " make your move (x y):");
                        int x = Integer.valueOf(String.valueOf(input.charAt(0)));
                        int y = Integer.valueOf(String.valueOf(input.charAt(input.length() - 1)));
                        game.playerMark(x, y);
                        uc.println("Coordinates [" + x + ", " + y + "] marked.");
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
                Thread.sleep(2000);

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

            exit = !uc.readYN("Play again?");

        } while(!exit);

        uc.println("Shutting down ...");
    }

}