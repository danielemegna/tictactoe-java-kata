package TicTacToe;

import java.io.*;

public class IOBridge {

    private final BufferedReader in;
    private final PrintStream out;

    public IOBridge(InputStream in, PrintStream out) {
        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = out;
    }

    public String readNotEmptyLineWithMessage(String message) {
        while(true) {
            try {
                print(message + " ");
                String input = in.readLine();

                if (!input.isEmpty())
                    return input;

            } catch(Exception ex) {}

            println("Invalid input, retry.");
        }
    }

    public void print(String message) {
        out.print(message);
    }

    public void println(String message) {
        out.println(message);
    }

}
