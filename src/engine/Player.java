package engine;

import renderer.renderables.Renderable;
import simulator.bodies.PhysicsBody;
import simulator.colliders.Collider;
import simulator.objects.PhysicsObject;

public class Player extends PhysicsObject {
    public Player(Renderable sprite, PhysicsBody physicsBody, float x, float y, float z) {
        super(sprite, physicsBody, x, y, z);
    }
}
