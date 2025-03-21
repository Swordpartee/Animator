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
import renderer.renderables.Crosshair;
import renderer.renderables.DrawVector;

public class App {
    private static Renderer renderer;
    private static CameraController playerController;
    private static RenderListener listener;
    private static Robot robot;

    private static AxisBox cube1;
    private static AxisBox cube2;
    private static AxisBox cube3;

    private static boolean cursorLocked = false;
    private static boolean isJustLocked = false;

    public static void main(String[] args) {
        playerController = new CameraController();
        listener = new RenderListener();
        renderer = new Renderer(() -> update(), playerController, listener);

        listener.addBinding(EventCode.EventType.KEY_PRESSED, EventCode.Q, () -> {
            cursorLocked = !cursorLocked;
            isJustLocked = true;
            if (cursorLocked) {
                renderer.setCursor(getInvisCursor());
                    
                robot.mouseMove(renderer.getWidth() / 2, renderer.getHeight() / 2);
            } else {
                renderer.setCursor(new Cursor(0));
            }
        });

        cube1 = new AxisBox(0, -1, 1, 3, 0.5, 0.5, Color.cyan);
        renderer.add(cube1);

        cube2 = new AxisBox(-2, 0, 0, 0.5, 4, 1, Color.pink);
        renderer.add(cube2);

        cube3 = new AxisBox(0, 0, 0, 1, 0.5, 4, Color.yellow);
        renderer.add(cube3);

        // // Adding a floor made of cubes
        // for (int x = -5; x <= 5; x++) {
        //     for (int z = -5; z <= 5; z++) {
        //         AxisBox floorTile = new AxisBox(x, -2, z, 1, 0.1, 1, Color.green);
        //         renderer.add(floorTile);
        //     }
        // }

        // Adding steps made of cubes
        for (int i = 0; i < 5; i++) {
            AxisBox step = new AxisBox(i, -1 + i * 0.5, i, 1, 0.5, 1, Color.gray);
            renderer.add(step);
        }

        DrawVector vector = new DrawVector(1, 3, 2, Color.black);
        renderer.add(vector);

        renderer.add(new Axis());

        renderer.add(new Crosshair(800, 600));

        renderer.start();

        try {
            robot = new Robot();
        } catch (AWTException e) {}

    }
    
    private static Cursor getInvisCursor() {
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        return Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "invisible");
    }

    private static void update() {
        playerController.move(listener);

        if (cursorLocked) {
            if (isJustLocked) {
                isJustLocked = false;
            } else {
                playerController.rotateRightOnPlane(-listener.getMousePos().getX() * 0.1);
                playerController.rotateUp(-listener.getMousePos().getY() * 0.1);
                
                robot.mouseMove(renderer.getWidth() / 2, renderer.getHeight() / 2);
            }
        }
    }
}
