package simulator.bodies;

import java.util.List;
import simulator.colliders.Collider;

public interface PhysicsBody {
    public void updateCollision(List<PhysicsBody> bodies);

    public void applyVelocity();

    public void setCollider(Collider collider);

    public Collider getCollider();

    public void setVel(float x, float y, float z);

    public void setVel(float[] vel);

    public void addForce(float dx, float dy, float dz);

    public void addForce(float[] dforce);

    public void setPos(float x, float y, float z);

    public void setPos(float[] pos);

    public void movePos(float dx, float dy, float dz);

    public void movePos(float[] dpos);

    public float[] getPos();
}
