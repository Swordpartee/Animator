package renderer.display;

import java.awt.Graphics;

import util.Vector;

public class RenderGraphic {
    private final Graphics graphics;
    
    public RenderGraphic(Graphics g) {
        graphics = g;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void drawLine(Vector p1, Vector p2, Camera camera) {
        Vector screenP1 = camera.toScreenCoords(p1);
        Vector screenP2 = camera.toScreenCoords(p2);
        graphics.drawLine((int) screenP1.getX(), (int) screenP1.getY(), (int) screenP2.getX(), (int) screenP2.getY());
    }

    public void setColor(java.awt.Color color) {
        graphics.setColor(color);
    }

    public void clear() {
        graphics.clearRect(0, 0, 800, 600);
    }

}
