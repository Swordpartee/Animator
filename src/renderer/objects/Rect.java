package renderer.objects;

import renderer.display.Camera;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import animation.Animation;

public class Rect extends PhysicsObject {
    private float x;
    private float y;
    private float z;
    private float width;
    private float height;
    private float depth;
    private Color color;

    public Rect(float x, float y, float z, float width, float height, float depth, Color color) {
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
            {x - halfWidth, y - halfHeight, z - halfDepth},
            {x + halfWidth, y - halfHeight, z - halfDepth},
            {x + halfWidth, y + halfHeight, z - halfDepth},
            {x - halfWidth, y + halfHeight, z - halfDepth},
            {x - halfWidth, y - halfHeight, z + halfDepth},
            {x + halfWidth, y - halfHeight, z + halfDepth},
            {x + halfWidth, y + halfHeight, z + halfDepth},
            {x - halfWidth, y + halfHeight, z + halfDepth}
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

    public Animation moveTo(float x, float y, float z, int duration) {
        return new Animation() {
            @Override
            public String toString() {
                return "Rect - MoveTo";
            };

            @Override
            public void generate() {
                float startX = Rect.this.x;
                float startY = Rect.this.y;
                float startZ = Rect.this.z;

                for (int i = 0; i <= duration; i++) {
                    float frameX = startX + (x - startX) * i / duration;
                    float frameY = startY + (y - startY) * i / duration;
                    float frameZ = startZ + (z - startZ) * i / duration;
                    this.addFrame(() -> {
                        Rect.this.x = frameX;
                        Rect.this.y = frameY;
                        Rect.this.z = frameZ;
                    });
                }
            }
        };
    }

    public Animation scaleTo(float w, float h, float d, int duration) {
        return new Animation() {
            @Override
            public String toString() {
                return "Rect - ScaleTo";
            };

            @Override
            public void generate() {
                float startW = Rect.this.width;
                float startH = Rect.this.height;
                float startD = Rect.this.depth;

                for (int i = 0; i <= duration; i++) {
                    float frameW = startW + (w - startW) * i / duration;
                    float frameH = startH + (h - startH) * i / duration;
                    float frameD = startD + (d - startD) * i / duration;
                    this.addFrame(() -> {
                        Rect.this.width = frameW;
                        Rect.this.height = frameH;
                        Rect.this.depth = frameD;
                    });
                }
            };
        };
    }
}
