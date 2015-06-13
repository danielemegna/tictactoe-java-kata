import TicTacToe.Cell.AlreadyMarkedCellAttemptException;
import TicTacToe.ComputerPlayer.SystematicComputerPlayer;
import TicTacToe.Coordinates.CoordinateOutOfBoundsException;
import TicTacToe.*;
import TicTacToe.Display.TerminalDisplay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintStream ps = System.out;

        ps.println("Welcome!");
        ps.print("What's your name? ");
        String name = br.readLine();

        boolean exit;
        do {


            Game game = new Game(name, new SystematicComputerPlayer(), new TerminalDisplay());
            ps.println("Are you ready " + game.getPlayerName() + "? We're starting!");

            game.updateDisplay(ps);

            while (true) {
                ps.print(game.getPlayerName() + " make your move (x y): ");

                try {
                    String input = br.readLine();
                    int x = Integer.valueOf(String.valueOf(input.charAt(0)));
                    int y = Integer.valueOf(String.valueOf(input.charAt(input.length() - 1)));
                    game.playerMark(x, y);
                    ps.println("Coordinates [" + x + ", " + y + "] marked.");
                } catch (CoordinateOutOfBoundsException ex) {
                    ps.println("Invalid coordinates.. retry");
                    continue;
                } catch (AlreadyMarkedCellAttemptException ex) {
                    ps.println("Cell already marked.. retry.");
                    continue;
                } catch (Exception ex) {
                    ps.println("Invalid input.. retry");
                    continue;
                }

                game.updateDisplay(ps);

                if (game.playerWon()) {
                    ps.println("Congratulations " + game.getPlayerName() + "! You won!");
                    break;
                }

                if (game.isMatrixFull()) {
                    ps.println("The grid is full.. tie!");
                    break;
                }

                ps.println("Computer is thinking...");
                Thread.sleep(2000);

                game.doTheNextComputerMove();
                game.updateDisplay(ps);

                if (game.computerWon()) {
                    ps.println("You lose, computer won!");
                    break;
                }
            }

            ps.print("Play again? (y/n) ");
            exit = !br.readLine().startsWith("y");

        } while(!exit);

        ps.println("Shutting down ...");
    }
}
