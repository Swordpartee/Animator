package renderer.display;

import java.awt.Color;
import java.awt.Graphics;

public class RenderGraphic {
    private final Graphics graphic;

    public RenderGraphic(Graphics g) {
        graphic = g;
    }

    public Graphics getGraphic() {
        return graphic;
    }

    public void drawLine(float[] p1, float[] p2, Camera camera) {
        if (camera.isVisible(p1) && camera.isVisible(p2)) {
            p1 = camera.convertTo2D(p1);
            p2 = camera.convertTo2D(p2);
            graphic.drawLine((int) p1[0], (int) p1[1], (int) p2[0], (int) p2[1]);
        }
    }

    public void setColor(Color color) {
        graphic.setColor(color);
    }
}
