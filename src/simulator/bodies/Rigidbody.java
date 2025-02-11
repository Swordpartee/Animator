package simulator.bodies;

import java.util.List;
import simulator.colliders.Collider;

public class Rigidbody implements PhysicsBody {
    private Collider collider;
    private float[] vel;
    private float[] pos;

    public Rigidbody(Collider collider, float x, float y, float z) {
        pos = new float[] {x, y, z};
        vel = new float[] {0, 0, 0};

        this.collider = collider;
    }

    @Override
    public void setVel(float x, float y, float z) {
        vel = new float[] {x, y, z};
    }

    @Override
    public void setVel(float[] vel) {
        this.vel = vel;
    }

    @Override
    public void addForce(float x, float y, float z) {
        vel[0] += x;
        vel[1] += y;
        vel[2] += z;
    }

    @Override
    public void addForce(float[] force) {
        vel[0] += force[0];
        vel[1] += force[1];
        vel[2] += force[2];
    }

    @Override
    public void setPos(float x, float y, float z) {
        pos = new float[] {x, y, z};

        collider.setPos(x, y, z);
    }

    @Override
    public void setPos(float[] pos) {
        this.pos = pos;

        collider.setPos(pos);
    }

    @Override
    public void movePos(float dx, float dy, float dz) {
        pos[0] += dx;
        pos[1] += dy;
        pos[2] += dz;

        collider.setPos(pos);
    }

    @Override
    public void movePos(float[] dpos) {
        pos[0] += dpos[0];
        pos[1] += dpos[1];
        pos[2] += dpos[2];

        collider.setPos(pos);
    }

    @Override
    public float[] getPos() {
        return pos;
    }

    @Override
    public void setCollider(Collider collider) {
        this.collider = collider;
    }

    @Override
    public Collider getCollider() {
        return collider;
    }

    @Override
    public void applyVelocity() {
        pos[0] += vel[0];
        pos[1] += vel[1];
        pos[2] += vel[2];
    }

    @Override
    public void updateCollision(List<PhysicsBody> bodies) {
        bodies.forEach(object -> {
            if (object.getCollider().collliding(collider)) {
                
            }
        });
    }

}
