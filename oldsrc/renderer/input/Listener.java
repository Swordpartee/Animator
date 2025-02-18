package renderer.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Listener extends KeyAdapter {
    private final boolean[] keys = new boolean[256];

    public boolean isKeyPressed(Keys key) {
        return key.getKeyCodes().stream().anyMatch(keyCode -> keys[keyCode]);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

}
