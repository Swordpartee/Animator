package simulator.colliders;

public class CubeCollider implements Collider {
    private final ColliderType type = ColliderType.CUBE;
    private float[] pos;
    private float[] size;

    public CubeCollider(float[] pos, float[] size) {
        this.pos = pos;
        this.size = size;
    }

    @Override
    public ColliderType getType() {
        return type;
    }

    @Override
    public float[] getPos() {
        return pos;
    }

    public float[] getSize() {
        return size;
    }

    @Override
    public void setPos(float x, float y, float z) {
        this.pos = new float[] {x, y, z};
    }

    @Override
    public void setPos(float[] pos) {
        this.pos = pos;
    }

    public void setSize(float height, float width, float depth) {
        this.size = new float[] {height, width, depth};
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
        float[] cubeMin = new float[] {
            this.pos[0] - this.size[0] / 2,
            this.pos[1] - this.size[1] / 2,
            this.pos[2] - this.size[2] / 2
        };
        float[] cubeMax = new float[] {
            this.pos[0] + this.size[0] / 2,
            this.pos[1] + this.size[1] / 2,
            this.pos[2] + this.size[2] / 2
        };
        float[] closestPoint = new float[3];

        for (int i = 0; i < 3; i++) {
            if (other.getPos()[i] < cubeMin[i]) {
            closestPoint[i] = cubeMin[i];
            } else if (other.getPos()[i] > cubeMax[i]) {
            closestPoint[i] = cubeMax[i];
            } else {
            closestPoint[i] = other.getPos()[i];
            }
        }

        float dx = other.getPos()[0] - closestPoint[0];
        float dy = other.getPos()[1] - closestPoint[1];
        float dz = other.getPos()[2] - closestPoint[2];
        float distanceSquared = dx * dx + dy * dy + dz * dz;

        return distanceSquared < (other.getRadius()) * (other.getRadius());
    }

    private boolean collideWithCube(CubeCollider other) {
        float[] thisMin = new float[] {
            this.pos[0] - this.size[0] / 2,
            this.pos[1] - this.size[1] / 2,
            this.pos[2] - this.size[2] / 2
        };
        float[] thisMax = new float[] {
            this.pos[0] + this.size[0] / 2,
            this.pos[1] + this.size[1] / 2,
            this.pos[2] + this.size[2] / 2
        };
        float[] otherMin = new float[] {
            other.getPos()[0] - other.getSize()[0] / 2,
            other.getPos()[1] - other.getSize()[1] / 2,
            other.getPos()[2] - other.getSize()[2] / 2
        };
        float[] otherMax = new float[] {
            other.getPos()[0] + other.getSize()[0] / 2,
            other.getPos()[1] + other.getSize()[1] / 2,
            other.getPos()[2] + other.getSize()[2] / 2
        };

        return (thisMax[0] >= otherMin[0] && thisMin[0] <= otherMax[0]) &&
               (thisMax[1] >= otherMin[1] && thisMin[1] <= otherMax[1]) &&
               (thisMax[2] >= otherMin[2] && thisMin[2] <= otherMax[2]);
    }
}
