package end2end;

import TicTacToe.Display.ConsoleDisplay;
import TicTacToe.Game.Game;
import TicTacToe.IOBridge;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class End2EndTest {

    private Game game;
    private ByteArrayOutputStream outputStream;

    @Test
    public void humanVsHuman_1() {
        assertGamePlayWithInputAndExpectedOutputFiles(
            "humanVsHuman1_input.txt",
            "humanVsHuman1_expectedOutput.txt"
        );
    }

    @Test
    public void computerVsComputer() {
        assertGamePlayWithInputAndExpectedOutputFiles(
            "computerVsComputer_input.txt",
            "computerVsComputer_expectedOutput.txt"
        );
    }

    private void assertGamePlayWithInputAndExpectedOutputFiles(String inputFileName, String outputFileName) {
        String inputString = getContentFromFilename(inputFileName);
        String expected = getContentFromFilename(outputFileName);

        initGameWithInputString(inputString);
        game.play();

        String actual = outputStream.toString();
        assertEquals(expected, actual);
    }

    private void initGameWithInputString(String inputString) {
        byte[] inputBytes = inputString.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputBytes);
        outputStream = new ByteArrayOutputStream();

        IOBridge ioBridge = new IOBridge(inputStream, new PrintStream(outputStream));
        ConsoleDisplay display = new ConsoleDisplay(ioBridge);
        game = new Game(display);
    }

    private String getContentFromFilename(String filename) {
        try {
            String relativePath = getClass().getResource(filename).getPath();
            Path absolutePath = Paths.get(relativePath).toAbsolutePath();
            return new String(Files.readAllBytes(absolutePath), StandardCharsets.UTF_8);
        } catch(IOException ex) {
            fail("Cannot find resource file with name [" + filename + "]");
            throw new Error(ex);
        }
    }

}
