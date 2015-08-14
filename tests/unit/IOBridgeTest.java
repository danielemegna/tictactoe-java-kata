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
        initWithInputData("Daniele.");

        String readedLine = ioBridge.readNotEmptyLine("What's your name?");

        assertEquals("What's your name? ", outputStream.toString());
        assertEquals("Daniele.", readedLine);
    }

    @Test
    public void emptyLineIsAnInvalidInput() {
        String inputData = "\n" + "This is a valid message.\n";
        initWithInputData(inputData);

        String readedLine = ioBridge.readNotEmptyLine("Insert your message:");

        String expectedOutput = "Insert your message: " +
            "Invalid input, retry.\n" +
            "Insert your message: ";
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals("This is a valid message.", readedLine);
    }

    private void initWithInputData(String inputData) {
        inputStream = new ByteArrayInputStream(inputData.getBytes());
        outputStream = new ByteArrayOutputStream();
        ioBridge = new IOBridge(inputStream, new PrintStream(outputStream));
    }

}
