package unit;

import TicTacToe.IOBridge;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IOBridgeTest {

    private ByteArrayInputStream inputStream;
    private ByteArrayOutputStream outputStream;
    private IOBridge ioBridge;

    @Test
    public void readALine() {
        String inputData = "The user message.";

        inputStream = new ByteArrayInputStream(inputData.getBytes());
        outputStream = new ByteArrayOutputStream();
        ioBridge = new IOBridge(inputStream, new PrintStream(outputStream));

        String line = ioBridge.readNotEmptyLine("Insert your message:");

        assertEquals("Insert your message: ", outputStream.toString());
        assertEquals("The user message.", line);
    }

    @Test
    public void emptyLineIsAnInvalidInput() {
        String inputData = "\n"
            + "This is the good input.\n";

        String expectedLine = "This is the good input.";
        String expectedOutput = "Write down: " +
            "Invalid input, retry.\n" +
            "Write down: ";

        inputStream = new ByteArrayInputStream(inputData.getBytes());
        outputStream = new ByteArrayOutputStream();
        ioBridge = new IOBridge(inputStream, new PrintStream(outputStream));

        String line = ioBridge.readNotEmptyLine("Write down:");

        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(expectedLine, line);
    }

}
