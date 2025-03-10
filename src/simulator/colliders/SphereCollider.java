package simulator.colliders;

import util.Functions;
import util.Vector;
import util.shapes.Sphere;

public class SphereCollider extends Sphere implements Collider {
    private final ColliderType type = ColliderType.SPHERE;
    

    public SphereCollider(double x, double y, double z, double radius) {
        super(x, y, z, radius);
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
        double dx = getX() - other.getX();
        double dy = getY() - other.getY();
        double dz = getZ() - other.getZ();
        double distance = dx * dx + dy * dy + dz * dz;
        double radiuses = this.getRadius() + other.getRadius();
        return distance < radiuses * radiuses;
    }

    private boolean collidesWithBox(BoxCollider other) {
        Vector closestPoint = new Vector();
        closestPoint.setX(Functions.clamp(this.getX(), other.getMin().getX(), other.getMax().getX()));
        closestPoint.setY(Functions.clamp(this.getY(), other.getMin().getY(), other.getMax().getY()));
        closestPoint.setZ(Functions.clamp(this.getZ(), other.getMin().getZ(), other.getMax().getZ()));
        double dx = this.getX() - closestPoint.getX();
        double dy = this.getY() - closestPoint.getY();
        double dz = this.getZ() - closestPoint.getZ();
        return dx * dx + dy * dy + dz * dz < this.getRadius() * this.getRadius();
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
