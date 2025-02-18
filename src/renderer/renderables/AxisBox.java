package renderer.renderables;

import renderer.display.Camera;
import renderer.display.RenderGraphic;
import util.Point;

import java.awt.Color;

public class AxisBox implements Renderable {
    protected Point pos;
    protected Point size;
    protected Color color;

    public AxisBox(double x, double y, double z, double width, double height, double depth, Color color) {
        this.pos = new Point(x, y, z);
        this.size = new Point(width, height, depth);
        this.color = color;
    }

    public void setPos(double x, double y, double z) {
        pos.set(x, y, z);
    }

    public void setSize(double width, double height, double depth) {
        size.set(width, height, depth);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void paint(RenderGraphic g, Camera camera) {
        double halfWidth = size.getX() / 2;
        double halfHeight = size.getY() / 2;
        double halfDepth = size.getZ() / 2;

        g.setColor(color);

        Point[] points = {
            new Point( pos.getX() - halfWidth, pos.getY() - halfHeight, pos.getZ() - halfDepth ),
            new Point( pos.getX() + halfWidth, pos.getY() - halfHeight, pos.getZ() - halfDepth ),
            new Point( pos.getX() + halfWidth, pos.getY() + halfHeight, pos.getZ() - halfDepth ),
            new Point( pos.getX() - halfWidth, pos.getY() + halfHeight, pos.getZ() - halfDepth ),
            new Point( pos.getX() - halfWidth, pos.getY() - halfHeight, pos.getZ() + halfDepth ),
            new Point( pos.getX() + halfWidth, pos.getY() - halfHeight, pos.getZ() + halfDepth ),
            new Point( pos.getX() + halfWidth, pos.getY() + halfHeight, pos.getZ() + halfDepth ),
            new Point( pos.getX() - halfWidth, pos.getY() + halfHeight, pos.getZ() + halfDepth )
        };

        for (int i = 0; i < 4; i++) {
            Point p1 = points[i];
            Point p2 = points[(i + 1) % 4];
            Point p3 = points[i + 4];
            Point p4 = points[(i + 1) % 4 + 4];
            g.drawLine(p1, p2, camera);
            g.drawLine(p1, p3, camera);
            g.drawLine(p2, p4, camera);
            g.drawLine(p3, p4, camera);
        }

    }

    @Override
    public String toString() {
        return "Cube";
    }

}
