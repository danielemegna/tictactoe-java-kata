import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class UserCommunicator {

    private final BufferedReader br;
    private final PrintStream ps;

    public UserCommunicator(BufferedReader br, PrintStream ps) {
        this.br = br;
        this.ps = ps;
    }

    public String printMessageAndReadLine(String message) throws IOException {
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
            String input = printMessageAndReadLine(message + " (y/n)");

            if(input.startsWith("y"))
                return true;
            if(input.startsWith("n"))
                return false;

            println("Invalid input, retry.");
        }
    }

    public void print(String message) {
        ps.print(message);
    }

    public void println(String message) {
        ps.println(message);
    }

    private String readLine() throws IOException {
        return br.readLine();
    }
}
