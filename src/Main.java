import TicTacToe.Cell.AlreadyMarkedCellAttemptException;
import TicTacToe.Coordinates.CoordinateOutOfBoundsException;
import TicTacToe.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        boolean exit;
        do {
            System.out.println("Welcome!");
            System.out.print("What's your name? ");
            String name = r.readLine();

            Game game = new Game(name, new SystematicComputerPlayer());
            System.out.println("Are you ready " + game.getPlayerName() + "? We're starting!");

            while (true) {
                System.out.print(game.getPlayerName() + " do your game (x y): ");

                try {
                    String input = r.readLine();
                    String[] coordinates = input.split(" ");
                    int x = Integer.valueOf(coordinates[0]);
                    int y = Integer.valueOf(coordinates[1]);
                    game.playerMark(x, y);
                    System.out.println("Coordinates [" + x + ", " + y + "] marked.");
                } catch (CoordinateOutOfBoundsException ex) {
                    System.out.println("Invalid coordinates.. retry");
                    continue;
                } catch (AlreadyMarkedCellAttemptException ex) {
                    System.out.println("Cell already marked.. retry.");
                    continue;
                } catch (Exception ex) {
                    System.out.println("Invalid input.. retry");
                    continue;
                }

                if (game.playerWon()) {
                    System.out.println("Congratulations " + game.getPlayerName() + "! You won!");
                    break;
                }

                if (game.isMatrixFull()) {
                    System.out.println("The grid is full.. spare!");
                    break;
                }

                game.doAComputerGame();

                if (game.computerWon()) {
                    System.out.println("You lose, computer won!");
                    break;
                }

                if (game.isMatrixFull()) {
                    System.out.println("The grid is full.. spare!");
                    break;
                }
            }

            System.out.print("Play again? (y/n) ");
            exit = !r.readLine().startsWith("y");

        } while(!exit);

        System.out.println("Shutting down ...");

    }
}
