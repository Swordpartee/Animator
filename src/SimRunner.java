import java.awt.Color;

import controller.CameraController;
import java.awt.Cursor;
import renderer.display.Renderer;
import renderer.io.RenderListener;
import renderer.renderables.Axis;
import renderer.renderables.AxisBox;
import simulator.colliders.BoxCollider;
import simulator.physicsbodies.Physicsbody;
import simulator.physicsbodies.Rigidbody;
import util.Functions;
import util.Vector;

public class SimRunner {
    private static CameraController camera;
    private static RenderListener listener;
    private static Renderer renderer;
    private static AxisBox player;
    private static BoxCollider playerCollider;
    private static Rigidbody playerPhysicsbody;
    private static AxisBox ground;
    private static BoxCollider groundCollider;
    private static Rigidbody groundPhysicsbody;

    public static void main(String[] args) {
        // camera = new Camera(25, 10, 0, 0, 0, 0, 120, 800 / 600, 0.1, 100);
        camera = new CameraController();
        listener = new RenderListener();
        renderer = new Renderer(() -> update(), camera, listener);
        
        camera.getPosition().set(0, 2, 3);
        camera.setFOV(120);

        ground = new AxisBox(0, 0, 0, 10, 0.1, 10, Color.RED);
        groundCollider = new BoxCollider(0, 0, 0, 10, 0, 10);
        groundPhysicsbody = new Rigidbody(groundCollider, 1000000);
        // groundPhysicsbody.applyForce(0, .08, 0);
        renderer.add(ground);
        
        player = new AxisBox(3, 3, 3, 1, 1, 1, Color.BLACK);
        playerCollider = new BoxCollider(0, 3, 0, 1, 1, 1);
        playerPhysicsbody = new Rigidbody(playerCollider, 1);
        // playerPhysicsbody.applyForce(0, 0, 0.002);
        renderer.add(player);

        renderer.add(new Axis());

        renderer.setCursor(new Cursor(0));

        renderer.start();
    }

    public static void update() {
        // playerPhysicsbody.applyForce(0, -0.008, 0);
        
        playerPhysicsbody.updateCollision(new Physicsbody[] {groundPhysicsbody});

        playerPhysicsbody.updatePos();

        camera.getTarget().set(playerPhysicsbody.getCollider().getPos());

        camera.getPosition().set(Functions.addVectors(playerPhysicsbody.getCollider().getPos(), new Vector(0, 2, 3)));

        camera.move(listener);

        player.set(camera.getTarget());

        playerPhysicsbody.getCollider().setPos(camera.getTarget());
    }
}
