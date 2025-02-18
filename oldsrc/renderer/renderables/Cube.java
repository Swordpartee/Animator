package renderer.renderables;

import renderer.display.Camera;

import java.awt.Color;
import renderer.display.RenderGraphic;

public class Cube implements Renderable {
    protected float[] pos;
    protected float[] size;
    protected Color color;

    public Cube(float x, float y, float z, float width, float height, float depth, Color color) {
        super();

        this.pos = new float[]{x, y, z};
        this.size = new float[]{width, height, depth};
        this.color = color;
    }

    @Override
    public void setPos(float x, float y, float z) {
        pos[0] = x;
        pos[1] = y;
        pos[2] = z;
    }

    @Override
    public void paint(RenderGraphic g, Camera camera) {
        float halfWidth = size[0] / 2;
        float halfHeight = size[1] / 2;
        float halfDepth = size[2] / 2;

        g.setColor(color);

        float[][] points = {
                { pos[0] - halfWidth, pos[1] - halfHeight, pos[2] - halfDepth },
                { pos[0] + halfWidth, pos[1] - halfHeight, pos[2] - halfDepth },
                { pos[0] + halfWidth, pos[1] + halfHeight, pos[2] - halfDepth },
                { pos[0] - halfWidth, pos[1] + halfHeight, pos[2] - halfDepth },
                { pos[0] - halfWidth, pos[1] - halfHeight, pos[2] + halfDepth },
                { pos[0] + halfWidth, pos[1] - halfHeight, pos[2] + halfDepth },
                { pos[0] + halfWidth, pos[1] + halfHeight, pos[2] + halfDepth },
                { pos[0] - halfWidth, pos[1] + halfHeight, pos[2] + halfDepth }
        };

        for (int i = 0; i < 4; i++) {
            float[] p1 = points[i];
            float[] p2 = points[(i + 1) % 4];
            float[] p3 = points[i + 4];
            float[] p4 = points[(i + 1) % 4 + 4];
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

    public void setColor(Color color) {
        this.color = color;
    }

}
