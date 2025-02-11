package simulator.objects;

import simulator.bodies.PhysicsBody;
import renderer.renderables.Renderable;

public class PhysicsObject {
    private final Renderable sprite;
    private final PhysicsBody physicsBody;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public PhysicsObject(Renderable sprite, PhysicsBody physicsBody, float x, float y, float z) {
        this.sprite = sprite;
        this.physicsBody = physicsBody;

        sprite.setPos(x, y, z);
        physicsBody.setPos(x, y, z);
    }

    public Renderable getSprite() {
        return sprite;
    }

    public PhysicsBody getPhysicsBody () {
        return physicsBody;
    }

    public void setPos(float x, float y, float z) {
        sprite.setPos(x, y, z);
        physicsBody.setPos(x, y, z);
    }

    public void setPos(float[] pos) {
        setPos(pos[0], pos[1], pos[2]);
    }

    public void applyVelocity() {
        physicsBody.applyVelocity();
        setPos(physicsBody.getPos());
    }

}
