package helpers;

import TicTacToe.Player.GameMode;
import TicTacToe.Player.Player;
import TicTacToe.Player.PlayerFactory;

import java.util.ArrayList;
import java.util.List;

public class SpyPlayerFactory extends PlayerFactory {
    private List<String> calls = new ArrayList<>();

    public SpyPlayerFactory() {
        super(null);
    }

    @Override
    public List<Player> listFromGameMode(GameMode mode) {
        registerNewCall("listFromGameMode(" + mode +")");
        return new ArrayList<>();
    }

    public List<String> calls() {
        return calls;
    }

    private void registerNewCall(String method) {
        calls.add(method);
    }
}
