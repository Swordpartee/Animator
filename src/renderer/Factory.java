package renderer;

import java.awt.Color;
import renderer.display.Camera;
import renderer.input.Listener;
import renderer.renderables.Cube;

public class Factory {
    public static Cube createCube() {
        return new Cube(0,0,0,1,1,1, Color.BLACK);
    }

    public static Camera createCamera() {
        return new Camera(5, 5, 5, 0, 0, 2, 145.0f, 800.0f / 600.0f, 0.1f, 10f);
    }

    public static Listener createListener() {
        return new Listener();
    }
}
