package simulator.colliders;

import util.Functions;
import util.Vector;
import util.shapes.Box;

public class BoxCollider extends Box implements Collider {
    private final ColliderType type = ColliderType.BOX;

    public BoxCollider(double x, double y, double z, double width, double height, double depth) {
        super(x, y, z, width, height, depth);
    }

    public BoxCollider(Vector pos, Vector size) {
        super(pos, size);
    }

    @Override
    public ColliderType getType() {
        return type;
    }

    @Override
    public Vector getPos() {
        return super.get();
    }

    @Override
    public void setPos(Vector pos) {
        super.set(pos);
    }

    private boolean collidesWithSphere(SphereCollider other) {
        return other.collidesWith(this);
    }

    private boolean collidesWithBox(BoxCollider other) {
        double dx = Functions.abs(this.getX() - other.getX()) - (this.getWidth() + other.getWidth()) / 2;
        double dy = Functions.abs(this.getY() - other.getY()) - (this.getHeight() + other.getHeight()) / 2;
        double dz = Functions.abs(this.getZ() - other.getZ()) - (this.getDepth() + other.getDepth()) / 2;
        return dx < 0 && dy < 0 && dz < 0;
    }

    @Override
    public boolean collidesWith(Collider other) {
        return switch (other.getType()) {
            case SPHERE -> collidesWithSphere((SphereCollider) other);
            case BOX -> collidesWithBox((BoxCollider) other);
            default -> false;
        };
    }
}
