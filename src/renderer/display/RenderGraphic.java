package renderer.display;

import java.awt.Graphics;

import util.Point;

public class RenderGraphic {
    private final Graphics graphics;
    
    public RenderGraphic(Graphics g) {
        graphics = g;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void drawLine(Point p1, Point p2, Camera camera) {
        Point screenP1 = camera.convertTo2D(p1);
        Point screenP2 = camera.convertTo2D(p2);
        graphics.drawLine((int) screenP1.getX(), (int) screenP1.getY(), (int) screenP2.getX(), (int) screenP2.getY());
    }

    public void setColor(java.awt.Color color) {
        graphics.setColor(color);
    }

}
