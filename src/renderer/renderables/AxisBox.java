package renderer.renderables;

import renderer.display.RenderGraphic;
import util.Vector;
import util.shapes.Box;

import java.awt.Color;

public class AxisBox extends Box implements Renderable {
    protected Color color;

    public AxisBox(double x, double y, double z, double width, double height, double depth, Color color) {
        super(x, y, z, width, height, depth);
        this.color = color;
    }

    public AxisBox(Vector pos, Vector size, Color color) {
        super(pos, size);
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setPos(double x, double y, double z) {
        set(x, y, z);
    }

    @Override
    public void setPos(Vector pos) {
        set(pos);
    }

    @Override
    public void paint(RenderGraphic g) {
        Vector min = super.getMin();
        Vector max = super.getMax();

        g.setColor(color);

        Vector[] points = {
            new Vector(min.getX(), min.getY(), min.getZ()),
            new Vector(max.getX(), min.getY(), min.getZ()),
            new Vector(max.getX(), max.getY(), min.getZ()),
            new Vector(min.getX(), max.getY(), min.getZ()),
            new Vector(min.getX(), min.getY(), max.getZ()),
            new Vector(max.getX(), min.getY(), max.getZ()),
            new Vector(max.getX(), max.getY(), max.getZ()),
            new Vector(min.getX(), max.getY(), max.getZ())
        };

        for (int i = 0; i < 4; i++) {
            Vector p1 = points[i];
            Vector p2 = points[(i + 1) % 4];
            Vector p3 = points[i + 4];
            Vector p4 = points[(i + 1) % 4 + 4];
            g.drawLine(p1, p2);
            g.drawLine(p1, p3);
            g.drawLine(p2, p4);
            g.drawLine(p3, p4);
        }

    }

    @Override
    public String toString() {
        return "Box";
    }

}
