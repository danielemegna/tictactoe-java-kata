package helpers;

import TicTacToe.Cell.Board;
import TicTacToe.Cell.CellMarkSign;
import TicTacToe.Coordinates.Coordinates;
import TicTacToe.Player.Player;

import java.util.ArrayList;
import java.util.List;

public class SpyPlayer extends Player {
    private List<String> calls = new ArrayList<>();
    private boolean registrationEnabled = false;

    public SpyPlayer(CellMarkSign sign) {
        super(sign);
    }

    @Override
    public void doNextMove(Board board) {
        registerNewCall("doNextMove(" + board.getClass() + ")");
    }

    @Override
    public String getName() {
        registerNewCall("getName()");
        return "SpyPlayer";
    }

    public void startRegistration() {
        registrationEnabled = true;
    }

    public void stopRegistration() {
        registrationEnabled = false;
    }

    public List<String> calls() {
        return calls;
    }

    private void registerNewCall(String method) {
        if(registrationEnabled)
            calls.add(method);
    }
}
