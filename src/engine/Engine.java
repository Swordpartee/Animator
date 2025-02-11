package engine;

import renderer.Factory;
import renderer.display.*;
import renderer.input.*;
import renderer.renderables.Renderable;

public class Engine {
    private static final Camera camera = Factory.createCamera();   
    
    private static final Listener listener = Factory.createListener();

    public static final Renderer renderer = new Renderer(() -> update(), camera, listener);

    public static void register(Renderable object) {
        renderer.register(object);
    }

    public static void start() {
        renderer.requestFocus();
        renderer.start();
    }

    public static void update() {
        if (listener.isKeyPressed(Keys.UP)) {
            camera.move(-0.1f, 0, 0);
            camera.moveTarget(-0.1f, 0, 0);
        }
        if (listener.isKeyPressed(Keys.DOWN)) {
            camera.move(0.1f, 0, 0);
            camera.moveTarget(0.1f, 0, 0);
        }
        if (listener.isKeyPressed(Keys.LEFT)) {
            camera.move(0, 0, -0.1f);
            camera.moveTarget(0, 0, -0.1f);
        }
        if (listener.isKeyPressed(Keys.RIGHT)) {
            camera.move(0, 0, 0.1f);
            camera.moveTarget(0, 0, 0.1f);
        }
    }
}
