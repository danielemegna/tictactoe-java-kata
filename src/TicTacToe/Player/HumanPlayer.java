package TicTacToe.Player;

import TicTacToe.Cell.AlreadyMarkedCellAttemptException;
import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.CoordinateOutOfBoundsException;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Display.ConsoleDisplay;

public class HumanPlayer extends Player {

    private final String name;
    private final ConsoleDisplay display;

    private HumanPlayer(CellMarkSign playerCellSign, ConsoleDisplay display) {
        super(playerCellSign);
        this.display = display;
        this.name = display.askForHumanPlayerName();
    }

    @Override
    public void doNextMove(Board board) {
        while(true){
            try {
                markBoard(display.askForNextMove(name), board);
            } catch (CoordinateOutOfBoundsException ex) {
                display.invalidCoordinatesMessage();
                continue;
            } catch (AlreadyMarkedCellAttemptException ex) {
                display.alreadyMarkedCellMessage();
                continue;
            } catch (Exception ex) {
                display.invalidInputMessage();
                continue;
            }

            return;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public static Player build(ConsoleDisplay display, CellMarkSign playerCellSign) {
        return new HumanPlayer(playerCellSign, display);
    }
}
