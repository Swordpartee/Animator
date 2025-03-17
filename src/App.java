import java.awt.Color;

import controller.CameraController;
import javax.naming.ldap.UnsolicitedNotificationListener;
import renderer.display.Renderer;
import renderer.io.EventCode;
import renderer.io.RenderListener;
import renderer.renderables.Axis;
import renderer.renderables.AxisBox;
import renderer.renderables.DrawVector;

public class App {  
    static Renderer renderer;
    static CameraController camera;
    static RenderListener listener;
    
    static AxisBox cube1;
    static AxisBox cube2;
    static AxisBox cube3;

    static double curMousePos = 0;

    public static void main(String[] args) {
        camera = new CameraController();
        listener = new RenderListener();
        renderer = new Renderer(() -> update(), camera, listener);

        camera.getPosition().set(0, 5, 20);

        listener.addBinding(EventCode.EventType.KEY_PRESSED, EventCode.SPACE, () -> {
            camera.getPosition().moveY(0.1);
            camera.getTarget().moveY(0.1);
        });

        cube1 = new AxisBox(0, 0, 0, 3, 0.5, 0.5, Color.cyan);
        renderer.add(cube1);

        cube2 = new AxisBox(0, 0, 0, 0.5, 4, 1, Color.pink);
        renderer.add(cube2);

        cube3 = new AxisBox(0, 0, 0, 1, 0.5, 4, Color.yellow);
        renderer.add(cube3);

        DrawVector vector = new DrawVector(1, 3, 2, Color.black);
        renderer.add(vector);

        renderer.add(new Axis());

        renderer.start();
        
    }
    
    public static void update() {
        camera.getTarget().setX(renderer.getMousePosition().getX());
        camera.getTarget().setY(renderer.getMousePosition().getY());
        if (listener.isKeyPressed(EventCode.W)) {
            camera.moveForwardOnPlane(0.1);
        }
        if (listener.isKeyPressed(EventCode.S)) {
            camera.moveForwardOnPlane(-0.1);
        }
        if (listener.isKeyPressed(EventCode.A)) {
            camera.moveLeftOnPlane(0.1);
        }
        if (listener.isKeyPressed(EventCode.D)) {
            camera.moveLeftOnPlane(-0.1);
        }
        if (listener.isKeyPressed(EventCode.RIGHT)) {
            camera.rotateOnPlane(0.5);
        }
        if (listener.isKeyPressed(EventCode.LEFT)) {
            camera.rotateOnPlane(-0.5);
        }
    }
}
