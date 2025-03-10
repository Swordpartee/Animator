import java.awt.Color;
import renderer.display.Camera;
import renderer.display.Renderer;
import renderer.renderables.Axis;
import renderer.renderables.AxisBox;
import simulator.colliders.BoxCollider;
import simulator.physicsbodies.Physicsbody;
import simulator.physicsbodies.Rigidbody;

public class App {
    private static AxisBox player;
    private static BoxCollider playerCollider;
    private static Rigidbody playerPhysicsbody;
    private static AxisBox ground;
    private static BoxCollider groundCollider;
    private static Rigidbody groundPhysicsbody;

    public static void main(String[] args) {
        Camera camera = new Camera(25, 10, 0, 0, 0, 0, 120, 800 / 600, 0.1, 100);
        Renderer renderer = new Renderer(() -> update(), camera);

        ground = new AxisBox(0, 0, 0, 10, 0.1, 10, Color.RED);
        groundCollider = new BoxCollider(0, 0, 0, 10, 0.1, 10);
        groundPhysicsbody = new Rigidbody(groundCollider, 1000000);
        renderer.registerRenderable(ground);
        
        player = new AxisBox(0, 3, 0, 1, 1, 1, Color.BLACK);
        playerCollider = new BoxCollider(0, 3, 0, 1, 1, 1);
        playerPhysicsbody = new Rigidbody(playerCollider, 1);
        renderer.registerRenderable(player);

        Axis axis = new Axis(3);
        renderer.registerRenderable(axis);

        renderer.start();
    }

    public static void update() {
        playerPhysicsbody.updatePos();
        playerPhysicsbody.updateCollision(new Physicsbody[] {groundPhysicsbody});
        playerPhysicsbody.applyForce(0, -0.025, 0);
        player.set(playerPhysicsbody.getCollider().getPos());
        // System.out.println(playerPhysicsbody.getVelocity());
        // System.out.println(playerPhysicsbody.getCollider().getPos());

    }
}
