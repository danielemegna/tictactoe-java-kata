package helpers;

import TicTacToe.IOBridge;

import java.util.ArrayList;
import java.util.List;

public class SpyIOBridge extends IOBridge implements Spy {

    private List<String> calls = new ArrayList<>();
    private boolean activatedSpy = false;
    private String nextReadLineOutput = "";

    public SpyIOBridge() {
        super(System.in, System.out);
    }

    @Override
    public void print(String message) {
        registerNewCall("print(" + message + ")");
    }

    @Override
    public void println(String message) {
        registerNewCall("println(" + message + ")");
    }

    @Override
    public String readNotEmptyLineWithMessage(String message) {
        registerNewCall("readNotEmptyLineWithMessage(" + message + ")");
        return nextReadLineOutput;
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

    public void setNextReadLineOutput(String readLineOutput) {
        this.nextReadLineOutput = readLineOutput;
    }
}
