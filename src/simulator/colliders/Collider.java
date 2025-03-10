package simulator.colliders;

import util.Vector;

public interface Collider {
    public ColliderType getType();

    public Vector getPos();

    public void setPos(Vector pos);

    public boolean collidesWith(Collider other);
}
