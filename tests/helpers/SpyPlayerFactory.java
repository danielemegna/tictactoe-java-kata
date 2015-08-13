package helpers;

import TicTacToe.Game.GameMode;
import TicTacToe.Player.Player;
import TicTacToe.Player.PlayerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpyPlayerFactory extends PlayerFactory {
    private final Player firstPlayer;
    private final Player secondPlayer;

    private List<String> calls = new ArrayList<>();
    private boolean activatedSpy = false;

    public SpyPlayerFactory(Player firstPlayer, Player secondPlayer) {
        super(null);
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    @Override
    public List<Player> listFromGameMode(GameMode mode) {
        registerNewCall("listFromGameMode(" + mode +")");
        return Arrays.asList(
            firstPlayer,
            secondPlayer
        );
    }

    public void activateSpy() {
        activatedSpy = true;
    }

    public void deactivateSpy() {
        activatedSpy = false;
    }

    public List<String> calls() {
        return calls;
    }

    private void registerNewCall(String method) {
        if(activatedSpy)
            calls.add(method);
    }
}
