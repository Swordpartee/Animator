import java.awt.Color;

import controller.CameraController;
import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import renderer.display.Renderer;
import renderer.io.EventCode;
import renderer.io.RenderListener;
import renderer.renderables.Axis;
import renderer.renderables.AxisBox;
import renderer.renderables.DrawVector;

public class App {  
    private static Renderer renderer;
    private static CameraController camera;
    private static RenderListener listener;
    private static Robot robot;

    private static AxisBox cube1;
    private static AxisBox cube2;
    private static AxisBox cube3;

    private static boolean cursorLocked = false;
    private static boolean isJustLocked = false;

    public static void main(String[] args) {
        camera = new CameraController();
        listener = new RenderListener();
        renderer = new Renderer(() -> update(), camera, listener);

        camera.getPosition().set(0, 2, 3);
        camera.setFOV(120);

        listener.addBinding(EventCode.EventType.KEY_PRESSED, EventCode.SPACE, () -> {
            camera.getPosition().moveY(0.1);
            camera.getTarget().moveY(0.1);
        });

        listener.addBinding(EventCode.EventType.KEY_PRESSED, EventCode.Q, () -> {
            cursorLocked = !cursorLocked;
            isJustLocked = true;
            if (cursorLocked) {
                renderer.setCursor(getInvisCursor());
                centerCursor();
            } else {
                renderer.setCursor(new Cursor(0));
            }
        });

        listener.addBinding(EventCode.EventType.MOUSE_MOVED, EventCode.MOUSE_MOVED, () -> {
            if (cursorLocked) {
                if (isJustLocked) {
                    isJustLocked = false;
                } else {
                    camera.rotateRightOnPlane(-listener.getMousePos().getX() * 0.1);
                    camera.rotateUp(-listener.getMousePos().getY() * 0.1);
                    centerCursor();
                }
            }
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

        try {
            robot = new Robot();
        } catch (AWTException e) {
        }

    }
    
    private static Cursor getInvisCursor() {
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        return Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "invisible");
    }

    private static void centerCursor() {
        if (robot != null) {
            // Move cursor to center
            robot.mouseMove(400, 300);
        }
    }
    
    private static void update() {
        camera.move(listener);

        System.out.println(listener.getMousePos());
    }
}
