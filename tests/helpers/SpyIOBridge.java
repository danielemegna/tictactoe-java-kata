package helpers;

import TicTacToe.IOBridge;

import java.util.ArrayList;
import java.util.List;

public class SpyIOBridge extends IOBridge {

    private List<String> calls = new ArrayList<>();
    private boolean activatedSpy = false;

    public SpyIOBridge() {
        super(System.in, System.out);
    }

    @Override
    public void println(String message) {
        registerNewCall("println(" + message + ")");
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
