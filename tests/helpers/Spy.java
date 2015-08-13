package helpers;

import java.util.List;

public interface Spy {
    void activateSpy();
    void deactivateSpy();
    List<String> calls();
}
