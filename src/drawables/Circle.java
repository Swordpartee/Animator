package drawables;

import java.awt.Graphics;

import animations.Animation;

public class Circle extends Drawable {
    private int x;
    private int y;
    private int radius;

    public Circle(int x, int y, int radius) {
        super();

        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void paint(Graphics g) {
        g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    public String toString() {
        return "Circle";
    }

    public Animation moveTo(int x, int y, int duration) {
        return new Animation() {
            @Override
            public String toString() {
                return "Circle - MoveTo";
            };
            @Override
            public void generate() {
                int startX = Circle.this.x;
                int startY = Circle.this.y;

                for (int i = 0; i <= duration; i++) {
                    int frameX = startX + (x - startX) * i / duration;
                    int frameY = startY + (y - startY) * i / duration;
                    this.addFrame(() -> {
                        Circle.this.x = frameX;
                        Circle.this.y = frameY;
                    });
                }
            }
        };
    }

    public Animation scaleTo(int r, int duration) {
        return new Animation() {
            @Override 
            public String toString() {
                return "Circle - ScaleTo";
            };
            @Override
            public void generate() {
                int startR = Circle.this.radius;

                for (int i = 0; i <= duration; i++) {
                    int frameR = startR + (r - startR) * i / duration;
                    this.addFrame(() -> {
                        Circle.this.radius = frameR;
                    });
                }
            }
        };
    }

}
