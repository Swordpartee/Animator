package animation.animator;

import renderer.display.Renderer;
import renderer.display.Screen;
import renderer.renderables.Renderable;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import animation.animations.Animation;

public class Animator {
    private static final Queue<Animation> animations = new LinkedBlockingDeque<>();
    private static final AnimationCamera camera = new AnimationCamera(10, 10, 10, 0, 0, 0, 60.0f, 800.0f / 600.0f, 0.1f, 1000.0f);

    public static final Renderer renderer = new Renderer(
        new Screen(() -> update()), camera);
    
    public static void register(Renderable object) {
        renderer.register(object);
    }

    public static void add(Animation animation) {
        animations.add(animation);
    }

    public static void start() {
        renderer.requestFocus();
        renderer.start();
    }

    public static void update() {
        Runnable frame = animations.peek().nextFrame();
        if (frame != null) {
            frame.run();
        } else {
            animations.poll();
        }
    }

    public static Animation moveCameraTo(float x, float y, float z, int duration) {
        return camera.moveTo(x, y, z, duration);
    }

    public static Animation moveCameraTargetTo(float x, float y, float z, int duration) {
        return camera.moveTargetTo(x, y, z, duration);
    }

}
