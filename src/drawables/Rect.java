package drawables;

import animations.Animation;
import java.awt.Graphics;

public class Rect extends Drawable {
    private int x;
    private int y;
    private int width;
    private int height;

    public Rect(int x, int y, int width, int height) {
        super();

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void paint(Graphics g) {
        g.drawRect(x, y, width, height);
    }

    @Override
    public String toString() {
        return "Rect";
    }

    public Animation moveTo(int x, int y, int duration) {
        return new Animation() {
            @Override
            public String toString() {
                return "Rect - MoveTo";
            };
            @Override
            public void generate() {
                int startX = Rect.this.x;
                int startY = Rect.this.y;

                for (int i = 0; i <= duration; i++) {
                    int frameX = startX + (x - startX) * i / duration;
                    int frameY = startY + (y - startY) * i / duration;
                    this.addFrame(() -> {
                        Rect.this.x = frameX;
                        Rect.this.y = frameY;
                    });
                }
            }
        };
    }

    public Animation scaleTo(int w, int h, int duration) {
        return new Animation() {
            @Override 
            public String toString() {
                return "Rect - ScaleTo";
            };
            @Override
            public void generate() {
                int startW = Rect.this.width;
                int startH = Rect.this.height;

                for (int i = 0; i <= duration; i++) {
                    int frameW = startW + (w - startW) * i / duration;
                    int frameH = startH + (h - startH) * i / duration;
                    this.addFrame(() -> {
                        Rect.this.width = frameW;
                        Rect.this.height = frameH;
                    });
                }
            };
        };
    }
}
