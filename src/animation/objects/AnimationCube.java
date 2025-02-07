package animation.objects;

import animation.animations.Animation;
import java.awt.Color;
import renderer.objects.Cube;

public class AnimationCube extends Cube {

    public AnimationCube(float x, float y, float z, float width, float height, float depth, Color color) {
        super(x, y, z, width, height, depth, color);
    }

    public Animation moveTo(float x, float y, float z, int duration) {
        return new Animation() {
            @Override
            public String toString() {
                return "Rect - MoveTo";
            };

            @Override
            public void generate() {
                float startX = AnimationCube.this.x;
                float startY = AnimationCube.this.y;
                float startZ = AnimationCube.this.z;

                for (int i = 0; i <= duration; i++) {
                    float frameX = startX + (x - startX) * i / duration;
                    float frameY = startY + (y - startY) * i / duration;
                    float frameZ = startZ + (z - startZ) * i / duration;
                    this.addFrame(() -> {
                        AnimationCube.this.x = frameX;
                        AnimationCube.this.y = frameY;
                        AnimationCube.this.z = frameZ;
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
                float startW = AnimationCube.this.width;
                float startH = AnimationCube.this.height;
                float startD = AnimationCube.this.depth;

                for (int i = 0; i <= duration; i++) {
                    float frameW = startW + (w - startW) * i / duration;
                    float frameH = startH + (h - startH) * i / duration;
                    float frameD = startD + (d - startD) * i / duration;
                    this.addFrame(() -> {
                        AnimationCube.this.width = frameW;
                        AnimationCube.this.height = frameH;
                        AnimationCube.this.depth = frameD;
                    });
                }
            };
        };
    }
}
