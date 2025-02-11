package simulator.colliders;

public class SphereCollider implements Collider {
    private final ColliderType type = ColliderType.SPHERE;
    private float[] pos;
    private float radius;

    public SphereCollider(float[] pos, float radius) {
        this.pos = pos;
        this.radius = radius;
    }

    @Override
    public ColliderType getType() {
        return type;
    }

    @Override
    public float[] getPos() {
        return pos;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void setPos(float x, float y, float z) {
        this.pos = new float[] {x, y, z};
    }

    @Override
    public void setPos(float[] pos) {
        this.pos = pos;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public boolean collliding(Collider other) {
        switch (other.getType()) {
            case ColliderType.SPHERE -> {
                return collideWithSphere((SphereCollider) other);
            }
            case ColliderType.CUBE -> {
                return collideWithCube((CubeCollider) other);
            }
            default -> {
                return false;
            }
        }
    }

    private boolean collideWithSphere(SphereCollider other) {
        float dx = this.pos[0] - other.getPos()[0];
        float dy = this.pos[1] - other.getPos()[1];
        float dz = this.pos[2] - other.getPos()[2];
        float distanceSquared = dx * dx + dy * dy + dz * dz;
        float radiusSquared = (this.radius + other.radius) * (this.radius + other.radius);
        return distanceSquared < radiusSquared;
    }

    private boolean collideWithCube(CubeCollider other) {
        float[] cubeMin = new float[] {
            other.getPos()[0] - other.getSize()[0] / 2,
            other.getPos()[1] - other.getSize()[1] / 2,
            other.getPos()[2] - other.getSize()[2] / 2
        };
        float[] cubeMax = new float[] {
            other.getPos()[0] + other.getSize()[0] / 2,
            other.getPos()[1] + other.getSize()[1] / 2,
            other.getPos()[2] + other.getSize()[2] / 2
        };
        float[] closestPoint = new float[3];

        for (int i = 0; i < 3; i++) {
            if (this.pos[i] < cubeMin[i]) {
            closestPoint[i] = cubeMin[i];
            } else if (this.pos[i] > cubeMax[i]) {
            closestPoint[i] = cubeMax[i];
            } else {
            closestPoint[i] = this.pos[i];
            }
        }

        float dx = this.pos[0] - closestPoint[0];
        float dy = this.pos[1] - closestPoint[1];
        float dz = this.pos[2] - closestPoint[2];
        float distanceSquared = dx * dx + dy * dy + dz * dz;

        return distanceSquared < (this.radius * this.radius);
    }
}
