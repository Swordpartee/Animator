package animation;

import renderer.display.Renderer;
import renderer.objects.PhysicsObject;

import java.awt.Graphics;
import java.awt.Canvas;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class Animator {
    private static final Queue<Animation> animations = new LinkedBlockingDeque<>();

    public static final Renderer renderer = new Renderer(new Canvas() {
        @Override
        public void paint(Graphics g) {
            Runnable frame = animations.peek().nextFrame();
            if (frame != null) {
                frame.run();
            } else {
                animations.poll();
            }
        }
    });

    public static Renderer getRenderer() {
        return renderer;
    }

    public static void register(PhysicsObject object) {
        renderer.register(object);
    }

    public static void add(Animation animation) {
        animations.add(animation);
    }

    public static void start() {
        renderer.start();
    }

    public static Animation moveCameraTargetTo(float x, float y, float z, int duration) {
        return new Animation() {
            @Override
            public String toString() {
                return "Camera - MoveTargetTo";
            };

            @Override
            public void generate() {
                float[] target = renderer.getCamera().getTarget();

                for (int i = 0; i <= duration; i++) {
                    float dx = (x - target[0]) / duration;
                    float dy = (y - target[1]) / duration;
                    float dz = (z - target[2]) / duration;
                    this.addFrame(() -> {
                        renderer.getCamera().moveTarget(dx, dy, dz);
                    });
                }
            }
        };
    }

    public static Animation moveCameraTo(float x, float y, float z, int duration) {
        return new Animation() {
            @Override
            public String toString() {
                return "Camera - MoveTo";
            };

            @Override
            public void generate() {
                float[] target = renderer.getCamera().getPosition();

                for (int i = 0; i <= duration; i++) {
                    float dx = (x - target[0]) / duration;
                    float dy = (y - target[1]) / duration;
                    float dz = (z - target[2]) / duration;
                    this.addFrame(() -> {
                        renderer.getCamera().move(dx, dy, dz);
                    });
                }
            }
        };
    }
}
