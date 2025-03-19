package simulator.physicsbodies;

import simulator.colliders.Collider;
import util.Vector;

public interface Physicsbody {
    public double getMass();

    public Vector getVelocity();

    public Collider getCollider();

    public void setMass(double mass);

    public void setVelocity(Vector velocity);

    public void applyForce(Vector force);

    public void applyForce(double x, double y, double z);

    public void updatePos();

    public void updateCollision(Physicsbody[] bodies);

    public void update(Physicsbody[] bodies);
}
