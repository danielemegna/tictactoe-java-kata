package TicTacToe;

import java.io.*;

public class UserCommunicator {

    private final BufferedReader in;
    private final PrintStream out;

    public UserCommunicator(InputStream in, PrintStream out) {
        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = out;
    }

    public String readLine(String message) throws IOException {
        while(true) {
            print(message + " ");
            String input = readLine();

            if(!input.isEmpty())
                return input;

            println("Invalid input, retry.");
        }
    }

    public boolean readYN(String message) throws IOException {
        while(true) {
            String input = readLine(message + " (y/n)");

            if(input.startsWith("y"))
                return true;
            if(input.startsWith("n"))
                return false;

            println("Invalid input, retry.");
        }
    }

    public void print(String message) {
        out.print(message);
    }

    public void println(String message) {
        out.println(message);
    }

    private String readLine() throws IOException {
        return in.readLine();
    }
}
