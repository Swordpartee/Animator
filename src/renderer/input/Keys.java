package renderer.input;

import java.util.List;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public enum Keys {
    UP(KeyEvent.VK_W, KeyEvent.VK_UP),
    DOWN(KeyEvent.VK_S, KeyEvent.VK_DOWN),
    LEFT(KeyEvent.VK_A, KeyEvent.VK_LEFT),
    RIGHT(KeyEvent.VK_D, KeyEvent.VK_RIGHT),
    ESCAPE(KeyEvent.VK_ESCAPE, KeyEvent.VK_ESCAPE);

    private final List<Integer> keyCodes;

    Keys(int... keyCodes) {
        this.keyCodes = new ArrayList<>();
        for (int keyCode : keyCodes) {
            this.keyCodes.add(keyCode);
        }
    }

    public List<Integer> getKeyCodes() {
        return keyCodes;
    }

    public static Keys getKey(int keyCode) {
        for (Keys key : Keys.values()) {
            if (key.getKeyCodes().contains(keyCode)) {
                return key;
            }
        }
        return null;
    }

    public static Keys getKey(KeyEvent e) {
        return getKey(e.getKeyCode());
    }
}
