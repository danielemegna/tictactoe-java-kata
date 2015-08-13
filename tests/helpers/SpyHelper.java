package helpers;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SpyHelper {

    public static void assertCalls(Spy spy, String... expectedCalls) {
        assertEquals(Arrays.asList(expectedCalls), spy.calls());
    }

    public static void activate(Spy... spies) {
        for(Spy s : spies)
            s.activateSpy();
    }

    public static void assertCallsContains(Spy spy, String... expectedCalls) {
        for(String expectedCall : expectedCalls) {
            String failMessage = "Spy doesn't contains expected call to '" + expectedCall + "'";
            assertTrue(failMessage, spy.calls().contains(expectedCall));
        }
    }
}
