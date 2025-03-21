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

        // Draw filled faces with slight transparency for better visibility
        Color faceColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), 128);
        
        // Draw the faces in a consistent order (back-to-front) to reduce z-fighting
        // Calculate view direction for face culling and ordering
        
        // Back face (Z-max)
        g.drawPolygon(faceColor, true, points[4], points[5], points[6], points[7]);
        
        // Bottom face (Y-min)
        g.drawPolygon(faceColor, true, points[0], points[1], points[5], points[4]);
        
        // Left face (X-min)
        g.drawPolygon(faceColor, true, points[0], points[3], points[7], points[4]);
        
        // Right face (X-max)
        g.drawPolygon(faceColor, true, points[1], points[2], points[6], points[5]);
        
        // Top face (Y-max)
        g.drawPolygon(faceColor, true, points[3], points[2], points[6], points[7]);
        
        // Front face (Z-min) - draw this last as it's most likely to be visible
        g.drawPolygon(faceColor, true, points[0], points[1], points[2], points[3]);
        
        // Draw wireframe outline
        g.setColor(Color.BLACK);
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
