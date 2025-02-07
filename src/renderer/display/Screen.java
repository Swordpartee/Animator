package renderer.display;

import java.awt.Canvas;
import java.awt.Graphics;

public class Screen extends Canvas {
    private final Runnable update;

    public Screen(Runnable update) {
        this.update = update;
    }

    @Override
    public void paint(Graphics g) {
        update.run();
    }
}
