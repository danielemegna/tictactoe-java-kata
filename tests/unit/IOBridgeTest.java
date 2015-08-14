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
    public void printAMessage() {
        initIOBridge();

        String message = "This is the message.";
        ioBridge.print(message);

        assertOutputStream(message);
    }

    @Test
    public void printAMessageWithNewLine() {
        initIOBridge();

        String message = "This is the message.";
        ioBridge.println(message);

        assertOutputStream(message + "\n");
    }

    @Test
    public void readALine() {
        initIOBridgeWithInputString("Daniele.");

        String readedLine = ioBridge.readNotEmptyLine("What's your name?");

        assertOutputStream("What's your name? ");
        assertEquals("Daniele.", readedLine);
    }

    @Test
    public void emptyLineIsAnInvalidInput() {
        String inputData = "\n" + "This is a valid message.\n";
        initIOBridgeWithInputString(inputData);

        String readedLine = ioBridge.readNotEmptyLine("Insert your message:");

        String expectedOutput = "Insert your message: " +
            "Invalid input, retry.\n" +
            "Insert your message: ";
        assertOutputStream(expectedOutput);
        assertEquals("This is a valid message.", readedLine);
    }

    private void initIOBridge() {
        initIOBridgeWithInputBytes(new byte[0]);
    }

    private void initIOBridgeWithInputString(String inputString) {
        initIOBridgeWithInputBytes(inputString.getBytes());
    }

    private void initIOBridgeWithInputBytes(byte[] inputBytes) {
        inputStream = new ByteArrayInputStream(inputBytes);
        outputStream = new ByteArrayOutputStream();
        ioBridge = new IOBridge(inputStream, new PrintStream(outputStream));
    }

    private void assertOutputStream(String message) {
        assertEquals(message, outputStream.toString());
    }
}
