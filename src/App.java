import java.awt.Color;
import renderer.display.Camera;
import renderer.display.Renderer;
import renderer.renderables.Axis;
import renderer.renderables.AxisBox;

public class App {
    public static void main(String[] args) {
        Camera camera = new Camera(5, 2, 0, 0, 0, 0, 120, 800 / 600, 0.1, 100);
        Renderer renderer = new Renderer(() -> {}, camera);
        
        AxisBox cube = (AxisBox) new AxisBox(0, 0, 0, 1, 1, 1, Color.BLACK);
        renderer.registerRenderable(cube);

        Axis axis = new Axis(3);
        renderer.registerRenderable(axis);

        renderer.start();
    }
}
