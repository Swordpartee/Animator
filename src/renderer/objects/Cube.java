package renderer.objects;

import renderer.display.Camera;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Cube implements Object {
    protected float x;
    protected float y;
    protected float z;
    protected float width;
    protected float height;
    protected float depth;
    protected Color color;

    public Cube(float x, float y, float z, float width, float height, float depth, Color color) {
        super();

        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.color = color;
    }

    @Override
    public void paint(Graphics g, Camera camera) {
        float halfWidth = width / 2;
        float halfHeight = height / 2;
        float halfDepth = depth / 2;

        g.setColor(color);

        float[][] points = {
                { x - halfWidth, y - halfHeight, z - halfDepth },
                { x + halfWidth, y - halfHeight, z - halfDepth },
                { x + halfWidth, y + halfHeight, z - halfDepth },
                { x - halfWidth, y + halfHeight, z - halfDepth },
                { x - halfWidth, y - halfHeight, z + halfDepth },
                { x + halfWidth, y - halfHeight, z + halfDepth },
                { x + halfWidth, y + halfHeight, z + halfDepth },
                { x - halfWidth, y + halfHeight, z + halfDepth }
        };

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform();

        for (int i = 0; i < 4; i++) {
            float[] p1 = camera.convertTo2D(points[i]);
            float[] p2 = camera.convertTo2D(points[(i + 1) % 4]);
            g2d.drawLine((int) p1[0], (int) p1[1], (int) p2[0], (int) p2[1]);

            float[] p3 = camera.convertTo2D(points[i + 4]);
            float[] p4 = camera.convertTo2D(points[(i + 1) % 4 + 4]);
            g2d.drawLine((int) p3[0], (int) p3[1], (int) p4[0], (int) p4[1]);

            g2d.drawLine((int) p1[0], (int) p1[1], (int) p3[0], (int) p3[1]);
        }

        g2d.setTransform(originalTransform);
    }

    @Override
    public String toString() {
        return "Rect";
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
